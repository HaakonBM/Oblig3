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
  
  @Override
  public boolean leggInn(T verdi) {
    Node<T> p = rot;
    Node<T> q=null;

    int cmp=0;
    while(p!=null){
      q=p;
      cmp= comp.compare(verdi, p.verdi);
      p= cmp < 0? p.venstre: p.høyre;

    }

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

  }// slutt leggInn
  
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
  public boolean fjern(T verdi)/////////////////Opg 5
  {

    if (verdi == null) return false;  // treet har ingen nullverdier

    Node<T> p = rot, q = null;   // q skal være forelder til p

    while (p != null)            // leter etter verdi
    {
      int cmp = comp.compare(verdi,p.verdi);      // sammenligner
      if (cmp < 0) { q = p; p = p.venstre; }      // går til venstre
      else if (cmp > 0) { q = p; p = p.høyre; }   // går til høyre
      else break;    // den søkte verdien ligger i p
    }
    if (p == null) return false;   // finner ikke verdi

    if (p.venstre == null && p.høyre == null){
      if (q < p){
       q.høyre = null;
      }
      else q.venstre = null;

      p.forelder = null;
    }

    if (p.venstre == null || p.høyre == null)  // Tilfelle 1) og 2)
    {
      Node<T> b = p.venstre != null ? p.venstre : p.høyre;  // b for barn
      if (p == rot) rot = b; b.forelder = null;
      else if (p == q.venstre) q.venstre = b; b.forelder = q;
      else q.høyre = b; b.forelder = q;
    }
    else  // Tilfelle 3)
    {
      Node<T> s = p, r = p.høyre;   // finner neste i inorden
      while (r.venstre != null)
      {
        s = r;    // s er forelder til r
        r = r.venstre;
      }

      p.verdi = r.verdi;   // kopierer verdien i r til p

      if (s != p) s.venstre = r.høyre; r.forelder = null;
      else s.høyre = r.høyre; r.forelder = null;
    }

    antall--;   // det er nå én node mindre i treet
    return true;

  }//fjern slutt

  public int fjernAlle(T verdi)////////////Opg 5
  {

    int counter = 0;

    if (verdi == null) {
      return 0;
    }

    Node<T> p = rot, q = null;   // q skal være forelder til p

    while (p != null)            // leter etter verdi
    {
      int cmp = comp.compare(verdi,p.verdi);      // sammenligner
      if (cmp < 0) { q = p; p = p.venstre; }      // går til venstre
      else if (cmp > 0) { q = p; p = p.høyre; }   // går til høyre
      else break;    // den søkte verdien ligger i p
    }
    if (p == null) return counter;   // finner ikke verdi

    if (p.venstre == null && p.høyre == null){
      if (q < p){
        q.høyre = null;
      }
      else q.venstre = null;

      p.forelder = null;
    }

    if (p.venstre == null || p.høyre == null)  // Tilfelle 1) og 2)
    {
      Node<T> b = p.venstre != null ? p.venstre : p.høyre;  // b for barn
      if (p == rot) rot = b; b.forelder = null;
      else if (p == q.venstre) q.venstre = b; b.forelder = q;
      else q.høyre = b; b.forelder = q;
    }
    else  // Tilfelle 3)
    {
      Node<T> s = p, r = p.høyre;   // finner neste i inorden
      while (r.venstre != null)
      {
        s = r;    // s er forelder til r
        r = r.venstre;
      }

      p.verdi = r.verdi;   // kopierer verdien i r til p

      if (s != p) s.venstre = r.høyre; r.forelder = null;
      else s.høyre = r.høyre; r.forelder = null;
    }

    antall--;   // det er nå én node mindre i treet
    counter++;

    fjernAlle(verdi);
  }//fjernAlle slutt
  
  @Override
  public int antall()
  {

    return antall;
  }
  
  public int antall(T verdi) {
    if(verdi==null) return 0;

    int n=0;

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

  }
  
  @Override
  public boolean tom()
  {
    return antall == 0;
  }
  
  @Override
  public void nullstill()//////////Opg 5
  {
      Node<T> p = rot, q = null; // q er forelder til p

      while ( rot != null) {
        p = rot; q= null;
        while (p.høyre =! null || p.venstre != null) {
          if(p.høyre != null && p.venstre != null){
            p = p.venstre;
          }
          else if(p.høyre == null) {
            p = p.venstre;
          }
          else if (p.venstre == null){
            p = p.høyre;
          }
        }
        p.høyre = null;
        p.venstre = null;
        p.forelder = null;
        p.verdi = null;
      }
  }
  
  private static <T> Node<T> nesteInorden(Node<T> p)
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
