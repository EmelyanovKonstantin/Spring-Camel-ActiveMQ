package ru.emelyanovkonstantin.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ru.emelyanovkonstantin.util.LocalDateDeserializer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class Message {

    private String mailTo;

    private String mailFrom;

    private String text;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate destinationDate;

    private Integer price;

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDestinationDate() {
        return destinationDate;
    }

    public void setDestinationDate(LocalDate destinationDate) {
        this.destinationDate = destinationDate;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }
}
