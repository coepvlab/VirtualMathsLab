/**
 * 
 */
package in.ac.coep.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * @author Prashant
 *
 */
@SuppressWarnings("serial")
@Component
@Entity
@Table(name = "systemConfig")
public class SystemConfig implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "systemConfigId")
	private long systemConfigId;
	
	private String configKey;
	
	private String configValue;

	/**
	 * @return the systemConfigId
	 */
	public long getSystemConfigId() {
		return systemConfigId;
	}

	/**
	 * @param systemConfigId the systemConfigId to set
	 */
	public void setSystemConfigId(long systemConfigId) {
		this.systemConfigId = systemConfigId;
	}

	/**
	 * @return the configKey
	 */
	public String getConfigKey() {
		return configKey;
	}

	/**
	 * @param configKey the configKey to set
	 */
	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}

	/**
	 * @return the configValue
	 */
	public String getConfigValue() {
		return configValue;
	}

	/**
	 * @param configValue the configValue to set
	 */
	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}
	
	
	
}
