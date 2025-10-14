import java.util.Scanner;

class Edge {
    int src,dest,weight;

    Edge(int s,int d,int w){
        src = s;
        dest = d;
        weight = w;
    }
}

class Graph {
    int V,E;
    Edge[] edges;

    Graph(int v,int e){
        V = v;
        E = e;
        edges = new Edge[e];
    }

    void addEdge(int index,int s,int d,int w){
        edges[index] = new Edge(s,d,w);
    }

    void bellmanFord(int src){
        int[] dist = new int[V];
        for(int i=0;i<V;i++){
            dist[i] = Integer.MAX_VALUE;
        }
        dist[src] = 0;
        for(int i=1;i<=V;i++){
            for(int j=0;j<E;j++){
                int u = edges[j].src;
                int v = edges[j].dest;
                int weight = edges[j].weight;
                if(dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]){
                    dist[v] = dist[u] + weight;
                }
            }
        }

        for(int j=0;j<E;j++){
            int u = edges[j].src;
            int v = edges[j].dest;
            int weight = edges[j].weight;
            if(dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]){
                System.out.println("Graph contains negative weight cycle");
                return;
            }
        }
        printSolution(dist, src);
    }

    void printSolution(int[] dist,int src){
        System.out.println("Vertex Distance from Source " + src);
        for(int i=0;i<V;i++){
            System.out.println(i + "\t\t" + dist[i]);
        }
    }
}

public class Bellman{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices:");
        int V = sc.nextInt();
        System.out.print("Enter number of edges:");
        int E = sc.nextInt();
        Graph graph = new Graph(V,E);
        System.out.println("Enter edges in the format: source destination weight");
        for(int i=0;i<E;i++){
            int s = sc.nextInt();
            int d = sc.nextInt();
            int w = sc.nextInt();
            graph.addEdge(i,s,d,w);
        }
        System.out.print("Enter source vertex:");
        int src = sc.nextInt();
        graph.bellmanFord(src);
        sc.close();
    }
}
