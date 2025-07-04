//package net.engineeringdigest.journalApp.scheduler;
//
//import net.engineeringdigest.journalApp.cache.AppCache;
//import net.engineeringdigest.journalApp.entity.JournalEntry;
//import net.engineeringdigest.journalApp.entity.User;
//import net.engineeringdigest.journalApp.enums.Sentiment;
//import net.engineeringdigest.journalApp.repository.UserRepositoryImpl;
//import net.engineeringdigest.journalApp.service.EmailService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Indexed;
//
//import java.time.LocalDateTime;
//import java.time.temporal.ChronoUnit;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Component
//public class UserScheduler {
//
//    @Autowired
//    private EmailService emailService;
//
//    @Autowired
//    private UserRepositoryImpl userRepository;
//
////    @Autowired
////    private SentimentAnalysisService sentimentAnalysisService;
//
//    @Autowired
//    private AppCache appCache;
//
//
//    @Scheduled(cron = "0 0 9 * * SUN")
//    public void fetchUserAndSendEmail(){
//        List<User> users= userRepository.getUsersForSA();
//        for(User user:users){
//            List<JournalEntry> journalEntryList = user.getJournalEntryList();
//            List<Sentiment> entryList = journalEntryList.stream()
//                    .filter(x -> x.getDate()
//                            .isAfter(LocalDateTime.now()
//                                    .minus(7, ChronoUnit.DAYS)))
//                    .map(x-> x.getSentiment())
//                    .collect(Collectors.toList());
//
//            Map<Sentiment, Integer> sentimentIntegerMap = new HashMap<>();
//            for(Sentiment sentiment : entryList){
//                if(sentiment != null){
//                    sentimentIntegerMap.put(sentiment, sentimentIntegerMap.getOrDefault(sentiment,0)+1);
//                }
//
//                Sentiment mostFreqSentiment = null;
//                int maxCount = 0;
//                for(Map.Entry<Sentiment, Integer>  entry : sentimentIntegerMap.entrySet()){
//                    if(entry.getValue() > maxCount){
//                        mostFreqSentiment = entry.getKey();
//                    }
//                }
//
//                if (mostFreqSentiment != null){
//                    emailService.sendEmail(user.getEmail(), "Sentiment for last 7 days", mostFreqSentiment.toString());
//                }
//
//            }
//        }
//
//    }
//
//    @Scheduled(cron = "*/10 * * * *")
//    public void clearAppCache(){
//          appCache.init();
//    }
//
//}
