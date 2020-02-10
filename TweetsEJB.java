package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.TweetEntity;
import twitter4j.DirectMessage;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Stateless
@LocalBean

public class TweetsEJB {

   @PersistenceContext
   private EntityManager em;
   
   public TweetsEJB(){}
   
   public void addNew(TweetEntity tweet)
   {
	   System.out.println("Adding Tweet");
	   em.persist(tweet);
	   System.out.println("!!!! Added successfully!!!");
   }
   
   @SuppressWarnings("unchecked")
public List<TweetEntity> getAllTweets(){
	   System.out.println("----Tweets---");
	   List<TweetEntity>tweets;
	   tweets = em.createQuery("SELECT t FROM tweets_tbl t").getResultList();
     return tweets;
	   
   }
   
   public void sendTweet(String message){
	   try{
		   ConfigurationBuilder configBuilder = new ConfigurationBuilder();
	  configBuilder.setDebugEnabled(true).setOAuthConsumerKey("3337545514-yblDMiV8i9jhG0N88EBHvQ8feNxje6zs4vE31uw");
	  configBuilder.setDebugEnabled(true)  .setOAuthConsumerSecret("288UeGNKmszrjT16MRCduUD0kFfhXGoHZYpSLtxsoijsn");
	  configBuilder.setDebugEnabled(true).setOAuthAccessToken("SYZH7JPylHPx6CDL5XoVOnY0Y");
	  configBuilder.setDebugEnabled(true) .setOAuthAccessTokenSecret("bmro944FfSz9R6FESu86MtfgyfGAKbgoplz1UPJmUXoP76dHe0");
	                                     
	           TwitterFactory tweetfactory;
	           tweetfactory = new TwitterFactory(configBuilder.build());
	           Twitter sender = tweetfactory.getInstance();
	           DirectMessage response = sender.sendDirectMessage("@AdhLecturer", message);
	           System.out.println("Sent: " + message + "to @" + response.getText());
	    }catch(Exception e){
	    	System.out.println("-----  Oooops did not send successfully ------");	    }
   }
}
