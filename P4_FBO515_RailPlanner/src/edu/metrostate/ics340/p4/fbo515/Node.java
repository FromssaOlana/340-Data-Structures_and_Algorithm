package edu.metrostate.ics340.p4.fbo515;

public class Node {
    @Override
    public String toString() {
	return String.format("%s", nameOfCity);
    }

    private String nameOfCity;

    public Node (String name){
        this.nameOfCity = name;
    }
    
    public String getNameOfCity() {
	return this.nameOfCity;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((nameOfCity == null) ? 0 : nameOfCity.hashCode());
	return result;
    }
    
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Node other = (Node) obj;
	if (nameOfCity == null) {
	    if (other.nameOfCity != null)
		return false;
	} else if (!nameOfCity.equals(other.nameOfCity))
	    return false;
	return true;
    }
}
