package game.lo4_data_structures.linkedlist

import game.enemyai.PlayerLocation

import scala.annotation.tailrec

// Simple Linked List implementation (note: Cannot represent empty list)
class LinkedListNode[A](var value: A, var next: LinkedListNode[A]) {



  /**
   * returns the number of nodes in the list starting with this node
   */
  @tailrec
  final def sizeTailRec(accumulatedSize: Int): Int = {
    if (this.next == null) {
      accumulatedSize + 1
    } else {
      this.next.sizeTailRec(accumulatedSize + 1)
    }
  }

  //Insert Don't insert correctly for some weird fuckin reason
  def insert(element: A): LinkedListNode[A] = {
    this.next = new LinkedListNode[A](element, this.next)
    this
  }


  def prepend(a: A): LinkedListNode[A] = {
    new LinkedListNode[A](a, this)
  }
  def size(): Int = {
    sizeTailRec(0)
  }


  override def toString: String = {
    if (this.next == null) {
      this.value.toString
    } else {
      this.value.toString + ", " + this.next.toString
    }
  }

  final def find(toFind: A): LinkedListNode[A] = {
    if (this.value == toFind) {
      this
    } else if (this.next == null) {
      null
    } else {
      this.next.find(toFind)
    }
  }

}
