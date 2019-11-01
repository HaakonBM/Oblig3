package no.oslomet.cs.algdat.Oblig3;

////////////////// ObligSBinTre /////////////////////////////////

import java.util.*;

public class ObligSBinTre<T> implements Beholder<T>
{
  private static final class Node<T>   // en indre nodeklasse
  {
    private T verdi;                   // nodens verdi
    private Node<T> venstre, høyre;    // venstre og høyre barn
    private Node<T> forelder;          // forelder

    // konstruktør
    private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder)
    {
      this.verdi = verdi;
      venstre = v; høyre = h;
      this.forelder = forelder;
    }

    private Node(T verdi, Node<T> forelder)  // konstruktør
    {
      this(verdi, null, null, forelder);
    }

    public Node(T verdi) {
      this(verdi, null,null, null);
    }

    @Override
    public String toString(){ return "" + verdi;}

  } // class Node

  private Node<T> rot;                            // peker til rotnoden
  private int antall;                             // antall noder
  private int endringer;                          // antall endringer

  private final Comparator<? super T> comp;       // komparator

  public ObligSBinTre(Comparator<? super T> c)    // konstruktør
  {
    rot = null;
    antall = 0;
    comp = c;
  }
  /////////////////////////// Oppgave-1 //////////////////////////////////
  @Override
  public boolean leggInn(T verdi) {

   Node<T> p = rot;
   Node<T> q=null;

   int cmp=0;
   while(p!=null){
     q=p;
     cmp= comp.compare(verdi, p.verdi);
     p= cmp < 0? p.venstre: p.høyre;
   }// end
    p= new Node<T>(verdi);
   if (q==null) rot = p;
   else if (cmp < 0) q.venstre= p;
   else q.høyre= p;

   if (q!=null){
     p.forelder=q;
   }
else {
  p.forelder= null; 
   }

 antall++;
return true;

  }
  ///////////////////////////////     Oppgave - 2 ////////////////////////////////////////
  @Override
  public boolean inneholder(T verdi)
  {
    if (verdi == null) return false;

    Node<T> p = rot;

    while (p != null)
    {
      int cmp = comp.compare(verdi, p.verdi);
      if (cmp < 0) p = p.venstre;
      else if (cmp > 0) p = p.høyre;
      else return true;
    }

    return false;
  }
  @Override
  public int antall()
  {
    return antall;
  }

  public int antall(T verdi) {
    if(verdi==null) return 0;

    int n=0; // Oppstartsverdi

    Node<T> p = rot;
    while (p != null) {
      int cmp = comp.compare(verdi,p.verdi);
      if (cmp < 0) p = p.venstre;      // går til venstre
      else if (cmp > 0) p = p.høyre;   // går til høyre
      else {
        n++;
        p = p.høyre;
      }
    }
    return n;
  }// slutt antall

  @Override
  public boolean tom()
  {
    return antall == 0;
  }

  @Override
  public boolean fjern(T verdi) {

    throw new UnsupportedOperationException("Ikke kodet ennå!");

  }// slutt fjern
 ///////////////////////////////// Oppgave -3 //////////////////////////////////////////
  private static <T> Node<T> nesteInorden(Node<T> p)
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  public int fjernAlle(T verdi)
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  

  
  @Override
  public void nullstill()
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  

  
  @Override
  public String toString()
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  public String omvendtString()
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  public String høyreGren()
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  public String lengstGren()
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  public String[] grener()
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  public String bladnodeverdier()
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  public String postString()
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  @Override
  public Iterator<T> iterator()
  {
    return new BladnodeIterator();
  }
  
  private class BladnodeIterator implements Iterator<T>
  {
    private Node<T> p = rot, q = null;
    private boolean removeOK = false;
    private int iteratorendringer = endringer;
    
    private BladnodeIterator()  // konstruktør
    {
      throw new UnsupportedOperationException("Ikke kodet ennå!");
    }
    
    @Override
    public boolean hasNext()
    {
      return p != null;  // Denne skal ikke endres!
    }
    
    @Override
    public T next()
    {
      throw new UnsupportedOperationException("Ikke kodet ennå!");
    }
    
    @Override
    public void remove()
    {
      throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

  } // BladnodeIterator

} // ObligSBinTre
