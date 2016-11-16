# Ford-Fulkerson
My implementation of Ford-Fulkerson algorithm.

This program was intended to be a meeting scheduler for Chris Murphy, that could help him automatically schedule meetings with students. Goal is to meet with every student within the minimum number of meetings, based on everybody's availability. Group meeting with more than one student is permitted.

This problem should really be solved by using the O(log(n)) approximation algorithm for Set Cover Problem, which is NP-complete. However, I mis-identified the problem in the first place and tried to cast it into a max network flow problem. Hence, comes this implementation of Ford-Fulkerson.

Nevertheless I kept this program, as a lesson. Really should clarify client's requirement before start coding. Also, Ford-Fulkerson is a very important and widely used algorithm, there is no harm to keep it.
