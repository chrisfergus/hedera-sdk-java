package com.hedera.sdk.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongycastle.util.encoders.Hex;

import com.hedera.sdk.common.HederaAccountID;
import com.hedera.sdk.common.HederaKey;
import com.hedera.sdk.common.HederaKey.KeyType;
/**
 * This class holds default values for an account creation
 * You can optionally create an instance of this class to set different default values
 * and re-use it on every account creation by passing it into the HederaCryptoCurrency.CreateAccount 
 * method for the account to override defaults
 */
public class HederaAccountCreateDefaults {
	final Logger logger = LoggerFactory.getLogger(HederaAccountCreateDefaults.class);
	private HederaAccount accountDefaultsFromClass = new HederaAccount();

	private HederaKey newRealmAdminPublicKey = accountDefaultsFromClass.newRealmAdminKey;
	private HederaAccountID proxyAccountID = accountDefaultsFromClass.proxyAccountID;
	/**
	 * The number of seconds before an account will auto renew
	 */
	public long autoRenewPeriodSeconds = accountDefaultsFromClass.autoRenewPeriod.seconds;
	/**
	 * The number of nanoseconds to add to the autoRenewPeriodSeconds 
	 */
	public int autoRenewPeriodNanos = accountDefaultsFromClass.autoRenewPeriod.nanos;
	/**
	 * Is a signature required to receive funds in this account
	 */
	public boolean receiverSignatureRequired = accountDefaultsFromClass.receiverSigRequired;
	/**
	 * when another account tries to proxy stake to this account, accept it only if the proxyFraction from that other account is at most maxReceiveProxyFraction
	 */
	public int maxReceiveProxyFraction = accountDefaultsFromClass.maxReceiveProxyFraction;
	/**
	 * payments earned from proxy staking are shared between the node and this account, with proxyFraction / 10000 going to this account
	 */
	public int proxyFraction = accountDefaultsFromClass.proxyFraction;
	/**
	 * create an account record for any transaction depositing more than this many tinybars
	 */
	public long receiveRecordThreshold = accountDefaultsFromClass.receiveRecordThreshold;
	/**
	 * create an account record for any transaction withdrawing more than this many tinybars
	 */
	public long sendRecordThreshold = accountDefaultsFromClass.sendRecordThreshold;

	// Methods
	/**
	 * Sets the account ID to proxy tokens to
	 * @param shardNum the shard number of the proxy account
	 * @param realmNum the realm number of the proxy account
	 * @param accountNum the account number of the proxy account
	 */
	public void setProxyAccountID(long shardNum, long realmNum, long accountNum) {
		this.proxyAccountID = new HederaAccountID(shardNum, realmNum, accountNum);
	}
	/**
	 * Resets the proxy account id to null
	 */
	public void resetProxyAccountID() {
		this.proxyAccountID = null;
	}
	/**
	 * Returns the {@link HederaAccountID} currently being proxied to
	 * @return {@link HederaAccountID}
	 */
	public HederaAccountID getProxyAccountID() {
		return this.proxyAccountID;
	}
	/**
	 * if realmID is -1, then this the admin key for the new realm that will be created
	 * it is ignored otherwise
	 * @param keyType the type of key
	 * @param newRealmAdminKey the new key
	 */
	public void setNewRealmAdminPublicKey(KeyType keyType,byte[] newRealmAdminKey) {
	   	logger.trace("Start - setNewRealmAdminPublicKey keyType {}, newRealmAdminKey {}", keyType, newRealmAdminKey);
		this.newRealmAdminPublicKey = new HederaKey(keyType, newRealmAdminKey);
	   	logger.trace("End - setNewRealmAdminPublicKey");
	}
	/**
	 * if realmID is -1, then this the admin key for the new realm that will be created
	 * it is ignored otherwise
	 * @param keyType the type of key
	 * @param newRealmAdminKeyHex the new key
	 */
	public void setNewRealmAdminPublicKey(KeyType keyType,String newRealmAdminKeyHex) {
	   	logger.trace("Start - setNewRealmAdminPublicKey keyType {}, newRealmAdminKeyHex {}", keyType, newRealmAdminKeyHex);
		this.newRealmAdminPublicKey = new HederaKey(keyType, Hex.decode(newRealmAdminKeyHex));
	   	logger.trace("End - setNewRealmAdminPublicKey");
	}
	/**
	 * gets the new realm admin public key
	 * @return {@link HederaKey}
	 */
	public HederaKey getNewRealmAdminPublicKey() {
	   	logger.trace("Start - getNewRealmAdminPublicKey");
	   	logger.trace("End - getNewRealmAdminPublicKey");
		return this.newRealmAdminPublicKey;
	}
}
