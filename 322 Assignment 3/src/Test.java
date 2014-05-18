
public class Test {
	public static void main(String[] args)
	{
		Graph graph = new Graph(21);
		graph.addEdge(1, 2);
		graph.addEdge(1, 6);
		graph.addEdge(6, 7);
		graph.addEdge(7, 8);
		graph.addEdge(8, 3);
		graph.addEdge(3, 4);
		graph.addEdge(8, 9);
		graph.addEdge(9, 10);
		graph.addEdge(10, 15);
		graph.addEdge(15, 20);
		graph.addEdge(7, 12);
		graph.addEdge(12, 13);
		graph.addEdge(13, 18);
		graph.addEdge(18, 17);
		graph.addEdge(14, 19);
		graph.setLabel(1, "A");
		graph.setLabel(2, "B");
		graph.setLabel(3, "C");
		graph.setLabel(4, "D");
		graph.setLabel(6, "E");
		graph.setLabel(7, "F");
		graph.setLabel(8, "G");
		graph.setLabel(9, "H");
		graph.setLabel(10, "I");
		graph.setLabel(12, "J");
		graph.setLabel(13, "K");
		graph.setLabel(14, "L");
		graph.setLabel(15, "M");
		graph.setLabel(17, "N");
		graph.setLabel(18, "O");
		graph.setLabel(19, "P");
		graph.setLabel(20, "Q");
		graph.depthFirstPrint(graph, 1);
	}
}
