package com.example.book.a_1_3;

/**
 * Created by Jiang on 2016/11/2.
 */

public class Node<T> {
  public T item;
  public Node<T> next;

  public Node() {
  }

  public Node(T i) {
    item = i;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{ ");
    if (item != null) sb.append(item.toString());
    Node temp = this;
    while (true) {
      if (temp.next == null) {
        break;
      }
      sb.append(", ");
      sb.append(temp.next.item.toString());
      temp = temp.next;
    }
    sb.append(" }");
    return sb.toString();
  }
}
