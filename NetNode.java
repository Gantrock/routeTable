/*
 * NetNode by Sean Hill 06/29/17
 */

import java.util.Date;
import java.util.HashMap;

public class NetNode {
	String ip;
	HashMap<Integer, NetNode> port;
	int numPorts;
	Date time;
	
	public NetNode() {}
	
	/**
	 * Creates a default Netnode
	 * @param theIp
	 */
	public NetNode(String theIp) {
		ip = theIp;
		numPorts = 0;
		time = new Date();
		time.setTime(System.currentTimeMillis());
		port = new HashMap<Integer, NetNode>();
	}
	
	/**
	 * Creates a NetNode with pre established ports
	 * @param theIp
	 * @param thePorts
	 */
	public NetNode(String theIp, NetNode[] thePorts) {
		ip = theIp;
		numPorts = 0;
		time = new Date();
		time.setTime(System.currentTimeMillis());
		port = new HashMap<Integer, NetNode>();
		for(int i = 0; i < thePorts.length; i++) {
			port.put(numPorts, thePorts[i]);
			numPorts++;
		}
	}
	
	/**
	 * Adds a single port to a NetNode
	 * @param theNode
	 */
	public void addPort(NetNode theNode) {
		port.put(numPorts, theNode);
		numPorts++;
		time.setTime(System.currentTimeMillis());
	}
	
	/**
	 * Adds several ports to a NetNode
	 * @param thePorts
	 */
	public void setPorts(NetNode[] thePorts){
		for(int i = 0; i < thePorts.length; i++) {
			port.put(numPorts, thePorts[i]);
			numPorts++;
		}
		time.setTime(System.currentTimeMillis());
	}
	
	/**
	 * Deletes a single port from a NetNode
	 * @param thePort
	 */
	public void delPort(int thePort){
		if(port.containsKey(thePort)) {
			port.remove(thePort);
		} else{
			System.out.println("Port does not exist");
		}
	}
	
	/**
	 * Returns the unmasked ip, an array of any ports (numerically) and the nodes time
	 */
	@Override
	public String toString() {
		return ("ip: " + ip +"\t Ports:" + port.keySet() + "\t\ttime: " + time);
		
	}
}
