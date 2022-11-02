package in.ac.coep.dao;

import java.util.List;

import in.ac.coep.entity.AnswerType;

public interface AnserTypeDao {

	public List<AnswerType> fetchAnswerType() throws Exception;
}
