package in.ac.coep.service;



/**
 * 
 * @author 
 *
 */
public interface CreatePdfReportService {	
		public void getAllDataForPDFReport() throws Exception;

		public void createSinglePDFResult(String date) throws Exception;
}
