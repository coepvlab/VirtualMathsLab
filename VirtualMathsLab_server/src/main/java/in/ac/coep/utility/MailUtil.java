package in.ac.coep.utility;

import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.json.JSONObject;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;



public class MailUtil {
/**
	 * @param template
	 *            name of html file template
	 * @param from
	 *            is from field of mail
	 * @param to
	 *            is list to recipients
	 * @param cc
	 *            is list cc recipients
	 * @param bcc
	 *            is list bcc recipients
	 * 
	 * @return void
	 * @since vtu 1.0
	 *        <p>
	 *        This method is used to send all types of mail which uses thymeleaf
	 *        templates.
	 * 
	 *        </p>
	 */
	public static synchronized void sendMail(Context context, String subject,
			String template, String from, String[] to, String[] cc,
			String bcc[], JavaMailSenderImpl mailSender,
			TemplateEngine classtemplateEngine) throws Exception {

		MimeMessage message1 = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message1, true,
				"UTF-8");
		helper.setFrom(from);
		helper.setTo(to);
		
		if (cc != null) {
			helper.setCc(cc);
		}
		if (bcc != null) {
			helper.setBcc(bcc);
		}
		
		
		Date tmp=new Date();
		
		helper.setSentDate(tmp);

		helper.setSubject(subject);
		
        //DataSource fds = new FileDataSource("D:\\Javas\\mailapi\\100.png");
		
		
		
		//FileSystemResource file = new FileSystemResource("E:\\log.txt");		
		//helper.addAttachment(file.getFilename(), file);
		
		
		String temp="\n ";
//		for(int i=0;to!=null && i<to.length;i++)
			temp+=to[0];
		
//		temp+="\n";
		
		
		System.out.println("**********\n VML Sending Mail from "+from+"  -to-  "+temp+"  --ON-- "+tmp+"\n**********");

		// create html body using thymeleaf
		final String htmlContent = classtemplateEngine.process(template,
				context);
		helper.setText(htmlContent, true);
		mailSender.send(message1);
		Date tmp1=new Date();
		
		System.out.println("**********\n VML Mail Sent from "+from+"  -to-  "+temp+"  --ON-- "+tmp1+"\n**********");

	}
	
	
	
	public static synchronized void sendMailforpdfreport(Context context, JSONObject data, String subject,
			String template, String from, String[] to, String[] cc,
			String bcc[], JavaMailSenderImpl mailSender,
			TemplateEngine classtemplateEngine) throws Exception {

		MimeMessage message1 = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message1, true,
				"UTF-8");
		helper.setFrom(from);
		helper.setTo(to);
		
		if (cc != null) {
			helper.setCc(cc);
		}
		if (bcc != null) {
			helper.setBcc(bcc);
		}
		
		
		Date tmp=new Date();
		
		helper.setSentDate(tmp);

		helper.setSubject(subject);
		
       
		
//		String filepath = "E:\\PdfReports\\"+ data.getString("EmailId")+"_"+data.getString("TestName")+".pdf";
		
//		FileSystemResource file = new FileSystemResource(filepath);		
//		helper.addAttachment(file.getFilename(), file);
		
		
		String temp="\n ";
//		for(int i=0;to!=null && i<to.length;i++)
			temp+=to[0];
//		
		temp+="\n";
		
		
		System.out.println("**********\n VML Sending Mail from "+from+"  -to-  "+temp+"  --ON-- "+tmp+"\n**********");

		// create html body using thymeleaf
		final String htmlContent = classtemplateEngine.process(template,
				context);
		helper.setText(htmlContent, true);
		mailSender.send(message1);
		Date tmp2=new Date();
		
		System.out.println("**********\n VML Mail Sent from "+from+"  -to-  "+temp+"  --ON-- "+tmp2+"\n**********");

	}
	
	
	
	public static synchronized void sendMailforForgotPassWord(Context context, String subject,
			String template, String from, String[] to, String[] cc,
			String bcc[], JavaMailSenderImpl mailSender,
			TemplateEngine classtemplateEngine) throws Exception {

		MimeMessage message1 = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message1, true,
				"UTF-8");
		helper.setFrom(from);
		helper.setTo(to);
		
		if (cc != null) {
			helper.setCc(cc);
		}
		if (bcc != null) {
			helper.setBcc(bcc);
		}
		
		
		Date tmp=new Date();
		
		helper.setSentDate(tmp);

		helper.setSubject(subject);
		
      
		
		String temp="\n ";
//		for(int i=0;to!=null && i<to.length;i++)
			temp+=to[0];
		
		temp+="\n";
		
		
		System.out.println("**********\n VML Sending Mail from "+from+"  -to-  "+temp+"  --ON-- "+tmp+"\n**********");

		// create html body using thymeleaf
		final String htmlContent = classtemplateEngine.process(template,
				context);
		helper.setText(htmlContent, true);
		mailSender.send(message1);
		
		Date tmp1=new Date();
		
		System.out.println("**********\n VML Mail Sent from "+from+"  -to-  "+temp+"  --ON-- "+tmp1+"\n**********");

	}
	
	
	
	
}
