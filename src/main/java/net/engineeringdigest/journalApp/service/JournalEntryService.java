package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

   private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        User findUserName = userService.findByUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
       JournalEntry saved =  journalEntryRepository.save(journalEntry);
        findUserName.getJournalEntryList().add(saved);
        logger.debug("hahah");
        userService.saveUser(findUserName);

    }

    public List<JournalEntry> getAllEntries(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }


    public void deleteById(ObjectId id, String userName){
            User findUserName = userService.findByUserName(userName);
            boolean removed =  findUserName.getJournalEntryList().removeIf(x -> x.getId().equals(id));
            if(removed){
                userService.saveUser(findUserName);
                journalEntryRepository.deleteById(id);
            }
    }
}
