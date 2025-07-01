//package net.engineeringdigest.journalApp.controller;
//
//import net.engineeringdigest.journalApp.entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/_journal")
//public class JournalEntryController {
//
//
//    private Map<String, JournalEntry> journalEntries = new HashMap<>();
//
//    @GetMapping
//    public List<JournalEntry> getAll(){
//            return new ArrayList<>(journalEntries.values());
//    }
//
//    @PostMapping
//    public boolean createEntry(@RequestBody JournalEntry journalEntry){
//        journalEntries.put(journalEntry.getId(), journalEntry);
//        return true;
//
//    }
//
//    @GetMapping("/id/{myId}")
//    public JournalEntry getJournalEntryByID(@PathVariable Long myId){
//       return journalEntries.get(myId);
//    }
//
//    @DeleteMapping("/id/{myId}")
//    public JournalEntry deleteJournalEntryByID(@PathVariable Long myId){
//        return journalEntries.remove(myId);
//    }
//
//    @PutMapping("/id/{id}")
//    public JournalEntry updateJournalById(@PathVariable String  id, @RequestBody JournalEntry journalEntry){
//        return journalEntries.put(id, journalEntry);
//
//    }
//}
