package in.ac.coep.dao;

import java.util.List;

import in.ac.coep.entity.MediaType;

public interface MediaTypeDao {

	public MediaType getMediaTypeById(int mediaTypeId) throws Exception;

	public  List<MediaType> fetchMediaType()  throws Exception;
}
