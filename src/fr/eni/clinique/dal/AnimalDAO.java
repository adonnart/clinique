package fr.eni.clinique.dal;

import java.util.List;

import fr.eni.clinique.bo.Animal;

public interface AnimalDAO extends DAO<Animal> {

	public Animal selectById(int id) throws DALException;

	public List<Animal> selectAnimalByClient(int codeClient) throws DALException;

}