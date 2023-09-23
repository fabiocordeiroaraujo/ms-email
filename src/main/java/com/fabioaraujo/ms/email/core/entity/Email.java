package com.fabioaraujo.ms.email.core.entity;

import com.fabioaraujo.ms.email.core.vo.EmailAddress;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Email {
    private UUID id;
    private String owner;
    private EmailAddress from;
    private List<EmailAddress> to;
    private List<EmailAddress> cc;
    private List<EmailAddress> bcc;
    private String subject;
    private String body;
    private LocalDateTime date;
    private Status status;
    private int retryCount;
    private LocalDateTime lastRetryDate;

    public Email(String id,
                 String owner,
                 String from, List<String> to,
                 List<String> cc, List<String> bcc,
                 String subject, String body, LocalDateTime date,
                 Status status,
                 int retryCount, LocalDateTime lastRetryDate){
        this.id = UUID.fromString(id);
        this.owner = owner;
        this.from = new EmailAddress(from);
        this.to = convertToEmailAddressList(to);
        this.cc = convertToEmailAddressList(cc);
        this.bcc = convertToEmailAddressList(bcc);
        this.subject = subject;
        this.body = body;
        this.date = date;
        this.status = status;
        this.retryCount = retryCount;
        this.lastRetryDate = lastRetryDate;
    }

    public Email(String owner, String from, List<String> to, List<String> cc, List<String> bcc, String subject, String body, LocalDateTime date){
        this.owner = owner;
        this.from = new EmailAddress(from);
        this.to = convertToEmailAddressList(to);
        this.cc = convertToEmailAddressList(cc);
        this.bcc = convertToEmailAddressList(bcc);
        this.subject = subject;
        this.body = body;
        this.date = date;
    }

    public Email(String owner, String from, List<EmailAddress> to, List<EmailAddress> cc, List<EmailAddress> bcc, String subject, String body, LocalDateTime date, int retryCount){
        this.owner = owner;
        this.from = new EmailAddress(from);
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.subject = subject;
        this.body = body;
        this.date = date;
        this.retryCount = retryCount;
    }

    public String getFrom(){
        return from.getAddress();
    }
    public void setFrom(String address){
        from = new EmailAddress(address);
    }

    public String[] getToAsArray() {
        return getEmailAddressesAsArray(this.to);
    }
    public String[] getCcAsArray() {
        return getEmailAddressesAsArray(this.cc);
    }
    public String[] getBccAsArray() {
        return getEmailAddressesAsArray(this.bcc);
    }

    private List<EmailAddress> convertToEmailAddressList(List<String> addressList) {
        List<EmailAddress> emailAddressList = new ArrayList<>();
        if (addressList != null) {
            for (String address : addressList) {
                emailAddressList.add(new EmailAddress(address));
            }
        }
        return emailAddressList;
    }

    private String[] getEmailAddressesAsArray(List<EmailAddress> emailList) {
        if (emailList == null) {
            return new String[0];
        }
        String[] addressesArray = new String[emailList.size()];
        for (int i = 0; i < emailList.size(); i++) {
            EmailAddress emailAddress = emailList.get(i);
            addressesArray[i] = emailAddress.getAddress();
        }
        return addressesArray;
    }
}
