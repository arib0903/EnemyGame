package game.enemyai

import game.enemyai.decisiontree.DecisionTreeValue
import game.lo4_data_structures.graphs.Graph
import game.lo4_data_structures.linkedlist.{LinkedListNode, Queue}
import game.lo4_data_structures.trees.BinaryTreeNode
import game.maps.GridLocation
import game.{AIAction, MovePlayer}


class AIPlayer(val id: String) {


  // TODO: Replace this placeholder code with your own
  def locatePlayer(playerId: String, playerLocations: LinkedListNode[PlayerLocation]): PlayerLocation = {
    if (playerLocations.next == null){
      playerLocations.value
    }
    else{
      if(playerLocations.value.playerId == playerId){
        playerLocations.value
      }
      else{
        locatePlayer(playerId,playerLocations.next)
      }
    }
  }

  def helper(playerLocations: LinkedListNode[PlayerLocation],sm:PlayerLocation,smallestDistance:Double,closestCurrentPlayer:PlayerLocation): PlayerLocation = {

    /**
     * @param playerLocations All the Player Location Nodes, containing (x,y) and "ID"
     * @param sm The AI Player Node, containing (x,y) and "ID"
     */

    var totalDist = math.sqrt(((playerLocations.value.x - sm.x)* (playerLocations.value.x - sm.x))+ ((playerLocations.value.y - sm.y)*(playerLocations.value.y - sm.y)))
//    println(playerLocations.value.playerId+ ":" + totalDist)
    if(playerLocations.next == null){
      if(smallestDistance>totalDist && totalDist!=0){
        playerLocations.value
         //Will be the last Player
      }
      else{
        closestCurrentPlayer //Will be the current closest player
      }
    }

    else{
     if(smallestDistance>totalDist && totalDist!=0){
       var thisCurrentClosestPlayer = playerLocations.value

       helper(playerLocations.next,sm,totalDist,thisCurrentClosestPlayer)

     }
      else{
       helper(playerLocations.next,sm,smallestDistance,closestCurrentPlayer)
     }

    }

  }

  // TODO: Replace this placeholder code with your own

  def closestPlayer(playerLocations: LinkedListNode[PlayerLocation]): PlayerLocation = {
    var ConstantPlayer = locatePlayer(this.id,playerLocations)//Initial AI Player(x1,y1)
    var startingDistanceToCompare:Double = 1000000000000.0
    var startingNode: PlayerLocation = new PlayerLocation(0,0,"")
    helper(playerLocations,ConstantPlayer,startingDistanceToCompare,startingNode)
  }


  // TODO: Replace this placeholder code with your own
  def computePath(start: GridLocation, end: GridLocation): LinkedListNode[GridLocation] = {

    //Starting from the bottom->up
    var x1 = end.x
    var x2 = start.x
    var y1 = end.y
    var y2 = start.y

    var UpdatedDirectionsToEnd: LinkedListNode[GridLocation] = new LinkedListNode[GridLocation](end,null)

    while(x1!=x2){
      if(x1>x2){
        x1 -= 1
        UpdatedDirectionsToEnd = UpdatedDirectionsToEnd.prepend(new GridLocation(x1,y1))

      }
      else{
        x1 += 1
        UpdatedDirectionsToEnd =  UpdatedDirectionsToEnd.prepend(new GridLocation(x1,y1))
      }
    }

    while(y1!=y2){

      if(y1>y2){
        y1 -= 1
        UpdatedDirectionsToEnd = UpdatedDirectionsToEnd.prepend(new GridLocation(x1,y1))
      }
      else{
        y1 +=1
        UpdatedDirectionsToEnd = UpdatedDirectionsToEnd.prepend(new GridLocation(x1,y1))
      }
    }
    UpdatedDirectionsToEnd
  }



  // TODO: Replace this placeholder code with your own
  def makeDecision(gameState: AIGameState, decisionTree: BinaryTreeNode[DecisionTreeValue]): AIAction = {

    val sm = decisionTree.value.check(gameState)
        if(decisionTree == null){
          null
        }

      else {
        if (sm < 0) {//If number is Negative
          makeDecision(gameState, decisionTree.left)
        }
        else if (sm > 0) { //If number is Positve
            makeDecision(gameState,  decisionTree.right)
        }
        else { // if Number is  == 0
          decisionTree.value.action(gameState)
        }
      }
  }

  def BFS[A](graph: Graph[A],start:Int,end:Int):Int ={
    val explored:Queue[Int] = new Queue()
    val toBeExplored:Queue[Int] = new Queue()
    var mapAcc: Map[Int,Int] = Map(start->0)
    explored.enqueue(start)
    toBeExplored.enqueue(start)
    while(!toBeExplored.empty()){
      val notToExplore = toBeExplored.dequeue()
      for(node <- graph.adjacencyList(notToExplore)){
        if(!explored.contains(node)){
          toBeExplored.enqueue(node)
          explored.enqueue(node)
          mapAcc = mapAcc + (node-> (mapAcc(notToExplore)+1))
        }
      }
    }
    mapAcc(end)
  }


  def distanceAvoidWalls(gameState: AIGameState,start:GridLocation,end:GridLocation): Int ={
    var builtGraph = gameState.levelAsGraph()
    val gridID: GridLocation => Int = location => location.x + (location.y * gameState.levelWidth)
    var x = gridID(start)
    var y = gridID(end)
    var z = BFS[GridLocation](builtGraph,x,y)
    z
  }

  // TODO: Replace this placeholder code with your own
  def closestPlayerAvoidWalls(gameState: AIGameState): PlayerLocation = {

    closestPlayer(gameState.playerLocations)
  }

  // TODO: Replace this placeholder code with your own
  def getPath(gameState: AIGameState): LinkedListNode[GridLocation] = {
    computePath(locatePlayer(this.id, gameState.playerLocations).asGridLocation(), closestPlayerAvoidWalls(gameState).asGridLocation())
  }




}

