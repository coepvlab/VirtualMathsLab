package in.ac.coep.utility;


/**
 * 
 */

/**
 * @author Laxmikant Kumbhare
 * @Date Sep 13, 2013
 *       <p>
 *       This class generates a highly secured passwords
 * 
 *       </p>
 */
public class PasswordGenerator {

	/**
	 * @author Laxmikant Kumbhare
	 * @Date Sep 13, 2013
	 * @return {@link String} Highly Secured Password
	 * @since vtu 1.0
	 *        <p>
	 *        This method generates randomized and system generated highly
	 *        secured password.
	 * 
	 *        </p>
	 */
	public static final String generatePassword() throws Exception {
		// TODO Auto-generated method stub
		// String newPass = UUID.randomUUID().toString();
		// return Md5Encryption.md5(newPass);

		String newPass = Long
				.toHexString(Double.doubleToLongBits(Math.random()));

		return newPass;
//		return Md5Encryption.md5(newPass);

	}
}
