package com.driver;

import java.util.*;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    LinkedList<Mail> inbox;
    LinkedList<Mail> trash;
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        inbox = new LinkedList<>();
        trash = new LinkedList<>();
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if(inbox.size() == inboxCapacity){
            Mail lastMail = inbox.getFirst();
            inbox.removeFirst();
            trash.add(lastMail);
        }
            Mail m = new Mail(date,sender,message);
            inbox.addLast(m);
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        Iterator<Mail> it = inbox.iterator();
        while(it.hasNext()){
            Mail m = it.next();
            if(m.getMessage().equals(message)){
                trash.add(m);
                it.remove();
            }
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(inbox.size() == 0){return null;}
        String latestMsg = inbox.getLast().getMessage();
        return latestMsg;

    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(inbox.size() == 0){return null;}
        String oldMsg = inbox.getFirst().getMessage();
        return oldMsg;

    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count = 0;

        for(Mail m : inbox){
            if((m.getDate().after(start) || m.getDate().equals(start)) &&
                    (m.getDate().before(end) || m.getDate().equals(end))){
                count++;
            }
        }
        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inbox.size();

    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();

    }

    public void emptyTrash(){
        // clear all mails in the trash
        if(trash.size()==0){return;}

        Iterator<Mail> it = trash.iterator();
        while(it.hasNext()){
            Mail m = it.next();
            it.remove();
        }
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
