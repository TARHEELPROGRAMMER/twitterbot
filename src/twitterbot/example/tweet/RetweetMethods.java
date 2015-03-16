/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitterbot.example.tweet;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

/**
 *
 * @author Joshua Powell
 * I give permission for you to use this source code for whatever purposes as long as I am credited with creating it inside
 * the code.
 */
public class RetweetMethods
{
    // This method uses 
    static public void RetweetTweet(String id)
    {
        try
        {
            String consumerKey = "EAnxkx1sOcS2EwoG9pcFjDwlf"; // key obtained after registering app.
            String consumerSecret = "C2cE7VoTiAziDndwCe60sc1OdPz3PnzTbLO52l4QlnxotUvNvb"; // secret key obtained from the registered app.
            String twitterToken = "622262310-AvIWEbJTfscAdbQ8hJavdIJrM6gtzE5LHc6kNOvh";
// access_token received by authentication user's twitter account
            String twitterSecret = "a7etCN9uvGiAtT7KXpngpyUahFiuOCRCTJLtAbqsRRpBi";
            // access_secret obtained by authentication user's twitter account
            String tweetId = id; //messageId of the tweet to be retweeted

            Twitter factory = new TwitterFactory().getInstance();
            Twitter twitter = factory;
            twitter.setOAuthConsumer(consumerKey, consumerSecret);
            AccessToken accessToken = new AccessToken(twitterToken, twitterSecret);
            twitter.setOAuthAccessToken(accessToken);
            twitter.retweetStatus(Long.valueOf(tweetId));
        } catch (Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
