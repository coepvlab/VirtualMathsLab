package in.ac.coep.utility;

import org.apache.commons.codec.binary.Base64;

	
public class EncoderDecoder {

	/**
	 * @param string
	 *            is an input data to encoder
	 * @return {@link String} returns endocded String
	 *         <p>
	 *         This method encodes input string in URL safe manner, so that it
	 *         can be sent though URL.
	 * 
	 *         </p>
	 */
	public static String encodeString(String string) throws Exception {

		String str = "";
		str = Base64.encodeBase64URLSafeString(string.getBytes());
		return str;
	}

	/**
	 * @param string
	 *            is an input data to encoder
	 * @return {@link String} returns decoded String
	 *         <p>
	 *         This method decodes the encoded String.
	 * 
	 *         </p>
	 */
	public static String decodeString(String string) throws Exception {
		String str = "";
		str = new String(Base64.decodeBase64(string), "UTF-8");
		return str;
	}

	public static void main(String[] args) throws Exception {

		String url = "ajax/camera/goToPresetPosition?pointName=Steam Flow Transmitter ";

		String str = EncoderDecoder.encodeString(url);
		System.out.println(str);

	}
}
