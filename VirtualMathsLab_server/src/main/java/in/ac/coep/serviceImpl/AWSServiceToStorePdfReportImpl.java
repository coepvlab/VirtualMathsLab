/**
 * 
 */
package in.ac.coep.serviceImpl;

import java.io.File;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

import in.ac.coep.service.AWSServiceToStorePdfReport;

/**
 * @author Prashant
 *
 */
@Service
public class AWSServiceToStorePdfReportImpl implements AWSServiceToStorePdfReport {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.ac.coep.service.AWSServiceToStorePdfReport#createAwsUrlForReport(java.
	 * io.File)
	 */
	@Override
	public String createAwsUrlForReport(File file) throws Exception {
		// TODO Auto-generated method stub

		AWSCredentials credentials = new BasicAWSCredentials("AKIAJCN4NDZ2U7E34PNQ",
				"z1+cZiiB5zC3i9RxlBXS9XbBWJlu9E9ZIZVV6HH4");

		AmazonS3 s3client = new AmazonS3Client(credentials);

		String bucketName = "otpuser1";

		// File convFile = new File(file.getName());
		// file.transferTo(convFile);

		Date expiration = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(expiration);
		cal.add(Calendar.DATE, 730); // add 2 year

		expiration = cal.getTime();

		s3client.putObject(new PutObjectRequest(bucketName, file.getName(), file)
				.withCannedAcl(CannedAccessControlList.PublicRead));

		GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName,
				file.getName());
		generatePresignedUrlRequest.setMethod(HttpMethod.GET);
		generatePresignedUrlRequest.setExpiration(expiration);

		URL url = s3client.generatePresignedUrl(generatePresignedUrlRequest);

		return url.toString();

	}

}
