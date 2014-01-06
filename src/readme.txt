Probelm2:
I used LinkedList to store points, streets, and intersections. 
GraphMaker runs BFS on G(point, street) to create intersections list/adjacency list.  

Problem 3:
ShortestPath uses an array and a linkedlist to store/update distance and edge of intersection. 
Java class PriorityQueue is used to implement min heap, hence popping an intersection
with the smallest distance is logV where V is the number of intersections. 
Relax eats up running time. 

Probelm4:
MinSpanningTree uses java class PriorityQueue to implement min heap. 
I created a comparator to compare streets by their distances to construct PriorityQueue of streets.
MST implementation is modified from the Sedgewick code. It uses Union/Find to check if the street 
would make a cycle. Union/Find is O(logV), but it does it E times at worst, 
so it's is O(ElogV) in the worst case. Since the edges(streets) are already sorted by storing them in
PriorityQueue, the overall running time is O(ElogV).

Proublem5: 
Breadth First Search class has HashMap to store if an intersection is marked, and 
a helper method to find the right intersection from a given point by iterating all the intersections.
BSF looks at the given intersection and puts it into a queue. 
After marking that intersection true, it iterates all the streets in the adjacency list
and adds each street's end point(intersection) to the intersection(key)'s list(value) in HashMap.
if it's not marked, it's added to the queue and marked. 
Do the same thing for the next element in the queue until the queue is empty. 

Problem6:
MinTurns uses BreadthFirstSearch class. The adjacency list of bfsTraversal is added to LevelQueue and
checks whether destination IntersectionI is in the queue. If not, the adjacency lists of the next level
are added to the queue, and index increments by one. If found the destination Intersection, 
it return the index.