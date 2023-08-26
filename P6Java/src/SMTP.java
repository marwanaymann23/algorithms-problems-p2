import java.util.ArrayList;
import java.util.Scanner;

class Route {
    int server1;
    int server2;
    int latency;

    public int getLatency() {
        return latency;
    }

    public Route(int server1, int server2, int latency) {
        this.server1 = server1;
        this.server2 = server2;
        this.latency = latency;
    }
}

class Case {
    public int n; // # Servers
    public int m; // # Routes
    public int S; // Source
    public int T; // Destination
    public ArrayList<Route> routes = new ArrayList<Route>();

    public Case(int n, int m, int s, int t) {
        this.n = n;
        this.m = m;
        S = s;
        T = t;
    }
    public Case() {
        n = 0;
        m = 0;
        S = 0;
        T = 0;
    }

    int getDirectRouteLatency(ArrayList<Route> caseRoutes, int source, int dest) {
        int route = 0;
        for (int i = 0; i < caseRoutes.size(); i++) {
            if ((caseRoutes.get(i).server1 == source) && (caseRoutes.get(i).server2 == dest)) {
                route = caseRoutes.get(i).latency;
            }
        }
        return route;
    }

    int getLatency(Case c1, int dest) {
        int latency = 0;
        for (int j = 0; j < c1.routes.size(); j++) {
            if ((c1.routes.get(j).server1 == dest - 1) && (c1.routes.get(j).server2 == dest)) {
                latency = c1.routes.get(j).latency;
            }
        }
        return latency;
    }

    int getShortestTime(ArrayList<Route> currentRoutes, Case currentCase) {

        if (currentRoutes.size() == 1)
            return currentRoutes.get(0).latency;

        else {
            int totalTime = 0;
            int directRouteLatency = 0;
            boolean temp = false;
            int destination = currentCase.T;

            while (!temp) {
                if (destination == currentCase.S) {
                    temp = true;
                } else {
                    int latency = getLatency(currentCase, destination);
                    destination--;
                    totalTime += latency;
                }
            }
            directRouteLatency = getDirectRouteLatency(currentCase.routes, currentCase.S, currentCase.T);
            /*if (directRouteLatency > sumOfSeconds) {
                return sumOfSeconds;
            } else {
                return directRouteLatency;
            }*/
            return Math.min(directRouteLatency, totalTime);
        }
    }
}

public class SMTP {

    static void print(ArrayList<String> output) {
        for (int i = 0; i < output.size(); i++) {
            System.out.println("Case #" + (i + 1) + ": " + output.get(i));
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<String> output = new ArrayList<String>();
        ArrayList<Route> caseRoute = new ArrayList<Route>();
        int numberOfCases = 0, numberOfServers = 0, numberOfRoutes = 0, source = 0, destination = 0, server1 = 0, server2 = 0, latency = 0;

        // Enter number of Cases
        numberOfCases = input.nextInt();
        for (int i = 0; i < numberOfCases; i++){
            numberOfServers = input.nextInt();
            numberOfRoutes = input.nextInt();
            source = input.nextInt();
            destination = input.nextInt();
            Case caseObject = new Case();
            if(source <= destination){
                caseObject = new Case(numberOfServers, numberOfRoutes, source, destination);
            }
            else {
                caseObject = new Case(numberOfServers, numberOfRoutes, destination, source);
            }
            if(numberOfRoutes == 0){
                output.add("Unreachable!");
                continue;
            }
            for (int j = 0; j < numberOfRoutes; j++){
                server1 = input.nextInt();
                server2 = input.nextInt();
                latency = input.nextInt();
                Route route = new Route(server1, server2, latency);
                caseObject.routes.add(route);
                caseRoute.add(route);
            }
            output.add(Integer.toString(caseObject.getShortestTime(caseRoute, caseObject)));
            caseRoute.clear();
        }
        print(output);
    }
}
