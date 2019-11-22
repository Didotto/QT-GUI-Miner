package server;

import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import mining.QTMiner;
import mining.ClusteringRadiusException;
import data.Data;
import database.DatabaseConnectionException;
import database.NoValueException;
import java.sql.SQLException;
import java.util.LinkedList;

import data.EmptyDatasetException;

/**
 * This class describes how the server works
 *
 */
public class ServeOneClient extends Thread {
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private QTMiner kmeans;
	
	/**
	 * Initializes the stream and start new thread which manages communication with a client
	 *
	 *@param socket work socket to manage communication with a client
	 *@throws IOException if an I/O error occurs while reading stream header
	 */
	
	public ServeOneClient(Socket socket) throws IOException{
		this.socket = socket;
		this.in = new ObjectInputStream(socket.getInputStream());
		this.out = new ObjectOutputStream(socket.getOutputStream());
		this.start();
	}
	
	/**
	 * Read the user choice as input and proceed to execute the operation chosen by the user.
	 * It can also manage new requests or close the connection.
	 * If a user sends an invalid input, an exception is thrown.
	 */
	
	public void run() {
		boolean repeat = true;
		try {
			do {
				System.out.println("[#] NEW REQUEST: " + socket);
				int choise = (int)in.readObject();
				switch(choise) {
					case 0:
						//0 - Store from DB
						try {
							String table = (String) in.readObject();
							Data data = new Data(table);
							out.writeObject("OK");
							if((int)in.readObject() == 1) {
								//1 - Learning from DB
								learningFromDb (data);
								boolean dvWindowOpen = true; 
								do {
									int answere = (int)in.readObject();
									if(answere == 2) {
										//2 - Store in file
										storeClustersInFile();
									
									}else {
										System.out.println("[#] The user closed his data visualization " + socket);
										dvWindowOpen=false;
									}
								}while(dvWindowOpen);
							}
						} catch(IOException e) {
							System.out.println("[!] Error occurred while communicating with the client " + e.getMessage());
							
						} catch (EmptyDatasetException e) {
							System.out.println(e.getMessage());
							out.writeObject("Table is empty");
						} catch (SQLException e) {
							System.out.println("[!] Error with the database: " + e.getMessage());
							System.out.println("[!] Error code: " + e.getErrorCode());
							System.out.println("[!] SQLState: " + e.getSQLState());
							System.out.println("I'm sending the problem");
							if(e.getErrorCode() == 1146 || e.getErrorCode() == 0) { //Error code if the table does not exist
								out.writeObject("Table does not exist");
							} else {
								out.writeObject("A problem occurred with the database server " + e.getMessage());
							}
							System.out.println("Problem sent");
						} catch (DatabaseConnectionException e) {
							System.out.println("[!] Error with the connection to the database: " + e.getMessage());
							out.writeObject(e.getMessage());
						} catch (NoValueException e) {
							System.out.println(e.getMessage());
							out.writeObject(e.getMessage());
						} catch (ClusteringRadiusException e) {
							System.out.println("[!] " + e.getMessage());
							out.writeObject(e.getMessage());
						}
						break;
						
					case 3:
						String fileName = (String)in.readObject();
						System.out.println("File name requested : "+ fileName);
						System.out.println("I'm sending the results");
						try {
							learningFromFile(fileName);
							System.out.println("Results sent");
						} catch(FileNotFoundException e) {
							System.out.println("[!] File " + fileName + " not found: " + e.getMessage());
							out.writeObject("File does not exist");
							System.out.println("Unable send results");
						}
						break;
					
					case -1:
						System.out.println("[#] The user closed his data visualization " + socket);
						break;
						
					case -2:	
						System.out.println("[#] ENDED: " + socket);
						repeat = false;
						break;
						
					default:
						System.out.println("Invalid Option: ");
						break;
				}
			} while(repeat);
			
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				socket.close();
			} catch(IOException e) {
				System.out.println("[!] Unable to close socket: " + socket);
			}
			
		}
		
	}
	
	/**
	 * Read the radius from stream, performs clustering and send to the client the scheme of the "table" and the 
	 * data clustered
	 * @param data the entire data set
	 * @throws IOException Signals that an I/O exception of some sort has occurred
	 * @throws ClassNotFoundException thrown when it tries to load in a class through its string name and something goes wrongs
	 * @throws ClusteringRadiusException thrown when is generates only one cluster
	 */
	
	private void learningFromDb (Data data) throws IOException, ClassNotFoundException, ClusteringRadiusException{
		double radius = (double) in.readObject();
		kmeans = new QTMiner(radius);
		kmeans.compute(data);
		out.writeObject("OK");
		//send scheme list
		LinkedList<String> scheme = kmeans.getSchemeList();
		scheme.addLast("Distance");
		out.writeObject(scheme);
		//send data
		out.writeObject(kmeans.getDataList(data));
	}
	
	/**
	 * Read the clustering from the file with name given in input and send the scheme , the centroid list and the radius
	 * to client
	 * @param fileName the name of the file from which to read a saved clustering
	 * @throws IOException Signals that an I/O exception of some sort has occurred
	 * @throws ClassNotFoundException thrown when it tries to load in a class through its string name and something goes wrongs
	 * @throws FileNotFoundException if the name doesn't does not match with any file saved
	 */
	
	private void learningFromFile (String fileName) throws FileNotFoundException, ClassNotFoundException, IOException{
		kmeans = new QTMiner(fileName);
		out.writeObject("OK");
		//send scheme
		out.writeObject(kmeans.getSchemeList());
		//send list of centroids
		out.writeObject(kmeans.getCentroidsList());
		//send radius
		out.writeObject(kmeans.getRadius());
	}
	
	/**
	 * Read the name of the file in which to save a clustering, 
	 * if the file already exists ask to client if overwrite he should overwrite
	 * @throws IOException Signals that an I/O exception of some sort has occurred
	 * @throws ClassNotFoundException thrown when it tries to load in a class through its string name and something goes wrongs
	 */
	
	private void storeClustersInFile() throws ClassNotFoundException, IOException{
		System.out.println("Saving file");
		String fileName = (String)in.readObject();
		System.out.println("FileName riceved: " + fileName);
		if(! new File(fileName + ".dmp").exists()) {
			out.writeObject("OK");
			kmeans.save(fileName + ".dmp");
			System.out.println("File saved " + fileName);
			out.writeObject("OK");
		} else {
			System.out.println("Unable to save file - File already exist");
			System.out.println("I'm asking for overwrite or not");
			out.writeObject("File already exist");
			String overwrite = (String)in.readObject();
			if(overwrite.toUpperCase().equals("Y")) {
				kmeans.save(fileName + ".dmp");
				System.out.println("File overwritted: " + fileName);
				out.writeObject("OK");
			} else {
				System.out.println("File was not overwritten");
			}
		}
	}
}
