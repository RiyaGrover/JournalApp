package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllJournalEntriesOfUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
     User findUserName = userService.findByUserName(userName);
     List<JournalEntry>  all =  findUserName.getJournalEntryList();
     if(all !=  null && !all.isEmpty()){
         return new ResponseEntity<>(all, HttpStatus.OK);
     }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry journalEntry){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        journalEntryService.saveEntry(journalEntry, userName);
        return new ResponseEntity<>(journalEntry , HttpStatus.CREATED);

    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryByID(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> collect = user.getJournalEntryList().stream()
                .filter(x -> x.getId()
                        .equals(myId))
                .toList();
        if (collect != null) {
            Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);

            if (journalEntry.isPresent()) {
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?>  deleteJournalEntryByID(@PathVariable ObjectId myId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
         journalEntryService.deleteById(myId, userName);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


//    @PutMapping("/id/{id}")
//    public JournalEntry updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntry journalEntry){
//        JournalEntry old = journalEntryService.findById(id).orElse(null);
//        if(old != null){
//            old.setTitle(journalEntry.getTitle());
//            old.setContent(journalEntry.getContent());
//        } else {
//            old.setTitle(old.getTitle());
//            old.setContent(old.getContent());
//        }
//        journalEntryService.saveEntry(journalEntry, userName);
//        return journalEntry;
//    }
}
