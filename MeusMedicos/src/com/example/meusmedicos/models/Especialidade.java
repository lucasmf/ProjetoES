package com.example.meusmedicos.models;

public class Especialidade implements Comparable<Especialidade>{
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((especialidade == null) ? 0 : especialidade.hashCode());
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
		Especialidade other = (Especialidade) obj;
		if (especialidade == null) {
			if (other.especialidade != null)
				return false;
		} else if (!especialidade.equals(other.especialidade))
			return false;
		return true;
	}

	private String especialidade;

    public Especialidade(String especialidade){
        setEspecialidade(especialidade);
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public int compareTo(Especialidade another) {
        return this.especialidade.compareTo(another.getEspecialidade());
    }

    @Override
    public String toString() {
        return especialidade;
    }
}
