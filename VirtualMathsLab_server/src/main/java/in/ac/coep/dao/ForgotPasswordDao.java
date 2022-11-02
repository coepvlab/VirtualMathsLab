package in.ac.coep.dao;

public interface ForgotPasswordDao {
	
	public long chekForgetPassData(Long uid, String emailId) throws Exception;
}
