package tests

import game.enemyai._
import game.lo4_data_structures.linkedlist.LinkedListNode
import game.maps.GridLocation
import org.scalatest._

class Task1 extends FunSuite {


  test("ReturnInputPlayer") {
    val loc1:PlayerLocation = new PlayerLocation(0,0,"ex1")
    val loc2:PlayerLocation= new PlayerLocation(0,1,"ex2")
    val loc3:PlayerLocation= new PlayerLocation(1,0,"ex3")
    val Linked3: LinkedListNode[PlayerLocation] = new LinkedListNode[PlayerLocation](loc3,null)
    val Linked2: LinkedListNode[PlayerLocation] = new LinkedListNode[PlayerLocation](loc2,Linked3)
    val Linked1: LinkedListNode[PlayerLocation] = new LinkedListNode[PlayerLocation](loc1,Linked2)


    val input: AIPlayer = new AIPlayer("ex2")
    val actual = input.locatePlayer("ex2",Linked1)


    //    println(Linked1.next.value.playerId)

    assert(actual.playerId == "ex2")





    // TODO

  }

  test("Closest Between 3 Nodes"){
    val loc1:PlayerLocation = new PlayerLocation(4,5,"ex1")
    val loc2:PlayerLocation= new PlayerLocation(8,9,"ex2")
    val loc3:PlayerLocation= new PlayerLocation(10,5,"ex3")
    val Linked3: LinkedListNode[PlayerLocation] = new LinkedListNode[PlayerLocation](loc3,null)
    val Linked2: LinkedListNode[PlayerLocation] = new LinkedListNode[PlayerLocation](loc2,Linked3)
    val Linked1: LinkedListNode[PlayerLocation] = new LinkedListNode[PlayerLocation](loc1,Linked2)
    val input: AIPlayer = new AIPlayer("ex1")

  input.closestPlayer(Linked1)
    val actual = input.closestPlayer(Linked1)

    assert(actual.playerId== "ex2")


  }
  test("Closest Between 2 Nodes"){
    val loc1:PlayerLocation = new PlayerLocation(4,5,"ex1")
    val loc2:PlayerLocation= new PlayerLocation(8,9,"ex2")
    val Linked2: LinkedListNode[PlayerLocation] = new LinkedListNode[PlayerLocation](loc2,null)
    val Linked1: LinkedListNode[PlayerLocation] = new LinkedListNode[PlayerLocation](loc1,Linked2)
    val input: AIPlayer = new AIPlayer("ex2")

    input.closestPlayer(Linked1)
    val actual = input.closestPlayer(Linked1)

    assert(actual.playerId== "ex1")

  }

  test("Closest Between Multiple Nodes"){
    val loc1:PlayerLocation = new PlayerLocation(0,3,"ex1")
    val loc2:PlayerLocation= new PlayerLocation(7,8,"ex2")
    val loc3:PlayerLocation = new PlayerLocation(2,1,"ex3")
    val loc4:PlayerLocation = new PlayerLocation(9,3,"ex4")
    val loc5:PlayerLocation = new PlayerLocation(4,6,"ex5")
    val loc6:PlayerLocation= new PlayerLocation(5,5,"ex6")
    val loc7:PlayerLocation = new PlayerLocation(4,0,"ex7")
    val loc8:PlayerLocation = new PlayerLocation(10,4,"ex8")

    val Linked8: LinkedListNode[PlayerLocation] = new LinkedListNode[PlayerLocation](loc8,null)
    val Linked7: LinkedListNode[PlayerLocation] = new LinkedListNode[PlayerLocation](loc7,Linked8)
    val Linked6: LinkedListNode[PlayerLocation] = new LinkedListNode[PlayerLocation](loc6,Linked7)
    val Linked5: LinkedListNode[PlayerLocation] = new LinkedListNode[PlayerLocation](loc5,Linked6)
    val Linked4: LinkedListNode[PlayerLocation] = new LinkedListNode[PlayerLocation](loc4,Linked5)
    val Linked3: LinkedListNode[PlayerLocation] = new LinkedListNode[PlayerLocation](loc3,Linked4)
    val Linked2: LinkedListNode[PlayerLocation] = new LinkedListNode[PlayerLocation](loc2,Linked3)
    val Linked1: LinkedListNode[PlayerLocation] = new LinkedListNode[PlayerLocation](loc1,Linked2)
    val input: AIPlayer = new AIPlayer("ex6")

    input.closestPlayer(Linked1)
    val actual = input.closestPlayer(Linked1)

    assert(actual.playerId== "ex5")

  }

  test("Increment/Decrement") {
    val input: AIPlayer = new AIPlayer("ex1")
    var StartEnd: LinkedListNode[GridLocation] = input.computePath(
      new GridLocation(1, 4),
      new GridLocation(3, 1)
    )
    //Should output: (1, 4), (1, 3), (1, 2), (1, 1), (2, 1), (3, 1)
    assert(StartEnd.value.x==1)
   println(StartEnd)
  }
  test("Decrement") {
    val input: AIPlayer = new AIPlayer("ex1")
    var StartEnd: LinkedListNode[GridLocation] = input.computePath(
      new GridLocation(6, 6),
      new GridLocation(3, 3)
    )
    //Should output: (6, 6), (6, 5), (6, 4), (6, 3), (5, 3), (4, 3), (3, 3)
    assert(StartEnd.value.x==6)
       println(StartEnd)
  }
  test("CheckDiagonal"){
    val input: AIPlayer = new AIPlayer("ex1")
    var StartEnd: LinkedListNode[GridLocation] = input.computePath(
      new GridLocation(6, 6),
      new GridLocation(3, 3)
    )
    //Should output: (6, 6), (6, 5), (6, 4), (6, 3), (5, 3), (4, 3), (3, 3)
    assert(StartEnd.size() == 7)
    //       println(StartEnd)
  }


}
