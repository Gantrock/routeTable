/*
 * Network by Sean Hill 06/29/17
 */

import java.util.HashMap;

public class Network {
	int sequence = 0;
	//ip address and NetNode
	HashMap<String, NetNode> nodes = new HashMap<String, NetNode>();
	
	/**
	 * Creates the default/hardcoded network
	 */
	public Network() {
		nodes.put("192.168.0.0", new NetNode("192.168.0.0"));
		nodes.put("192.168.0.1", new NetNode("192.168.0.1"));
		nodes.put("192.168.0.2", new NetNode("192.168.0.2"));
		nodes.put("192.168.0.3", new NetNode("192.168.0.3"));
		nodes.put("192.168.0.4", new NetNode("192.168.0.4"));
		//manually entering ports
		//0 to 1 and 2
		nodes.get("192.168.0.0").addPort(nodes.get("192.168.0.1"));
		nodes.get("192.168.0.0").addPort(nodes.get("192.168.0.2"));
		//1 to 0 and 3
		nodes.get("192.168.0.1").addPort(nodes.get("192.168.0.0"));
		nodes.get("192.168.0.1").addPort(nodes.get("192.168.0.3"));
		//2 to 0 and 3 and 4
		nodes.get("192.168.0.2").addPort(nodes.get("192.168.0.0"));
		nodes.get("192.168.0.2").addPort(nodes.get("192.168.0.3"));
		nodes.get("192.168.0.2").addPort(nodes.get("192.168.0.4"));
		//3 to 1 and 2 and 4
		nodes.get("192.168.0.3").addPort(nodes.get("192.168.0.1"));
		nodes.get("192.168.0.3").addPort(nodes.get("192.168.0.2"));
		nodes.get("192.168.0.3").addPort(nodes.get("192.168.0.4"));
		//4 to 2 and 3
		nodes.get("192.168.0.4").addPort(nodes.get("192.168.0.2"));
		nodes.get("192.168.0.4").addPort(nodes.get("192.168.0.3"));
	}
	
	/**
	 * Goes through every "router" and creates a string representation of a router table
	 * @param start
	 * @return
	 */
	public String findAll(String start) {
		String result = "";
		if(nodes.containsKey(start)) {
			for(String targets : nodes.keySet()) {
				if(!targets.equals(start)) {
					result += find(start, targets);
				}
			}
		}
		return result;
	}
	/**
	 * Creates a string representation of the route between two routers
	 * @param start
	 * @param theDest
	 * @return
	 */
	public String find(String start, String theDest) {
		int hops = 1;
		String result = "";
		if(nodes.containsKey(theDest)) {
			for(Integer portNum : nodes.get(start).port.keySet()){
				hops = 1;
				result += sequence + "\t\t" + theDest + "\t " + portNum + "\t " + findNodes(nodes.get(start).port.get(portNum), hops, theDest) + "\t " + nodes.get(theDest).time + "\n";
				sequence++;
			}
		} else if(start.equals(theDest)) {
			result = "Same address";
		}else {
			result = "address not found";
		}
		sequence++;
		return result;
	}
	
	/**
	 * Finds a route between two NetNodes, recursive.
	 * @param theNode
	 * @param hops
	 * @param theDest
	 * @return
	 */
	private int findNodes(NetNode theNode, int hops, String theDest) {
		for(Integer portNum : theNode.port.keySet()) {
			if(theNode.ip.equals(theDest)) {
				return hops;
			}else if(hops < 6 && !theNode.port.get(portNum).ip.equals(theNode.ip)){
				hops++;
				findNodes(theNode.port.get(portNum), hops, theDest);
			} else if(hops > 6){
				//maximum hops exceeded
				hops = 0;
			}
		}
		return hops;
	}
	
	/**
	 * Alters the Network by a hardcoded means, could be expanded
	 */
	public void alter(){
		//Alter the network to show change
		//Removes the connection between Nodes 2-3 and 4-3
		nodes.get("192.168.0.2").delPort(0);
		nodes.get("192.168.0.4").delPort(1);
	}
	
	/**
	 * Returns a String representation of every NetNode in a network
	 */
	public String toString() {
		String tester = "";
		for(String node : nodes.keySet())
		{
			tester += " " + nodes.get(node) + " \n";
		}
		return tester;
	}
}
