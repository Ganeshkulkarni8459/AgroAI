package org.agriopredict.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.agriopredict.common.RandomStringGenerator;
import org.agriopredict.dto.request.Note;
import org.agriopredict.dto.request.UserNoteRequest;
import org.agriopredict.dto.response.UserNoteResponse;
import org.agriopredict.entity.UserNote;
import org.agriopredict.repo.UserNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserNoteService {

    @Autowired
    private UserNoteRepository userNoteRepository;

    public UserNoteResponse addOrUpdateNotes(UserNoteRequest request) {
        UserNoteResponse userNoteResponse = new UserNoteResponse();

        // Validate input data
        if (request.getMobileNo() == 0 || request.getNotes() == null || request.getNotes().isEmpty()) {
            userNoteResponse.setMessage("Invalid input data");
            userNoteResponse.setResponseCode("400");
            return userNoteResponse;
        }

        // Iterate through each note in the request
        for (Note noteRequest : request.getNotes()) {
            if (noteRequest.getTitle() == null || noteRequest.getContent() == null ||
                noteRequest.getTitle().isEmpty() || noteRequest.getContent().isEmpty()) {
                userNoteResponse.setMessage("Invalid note data");
                userNoteResponse.setResponseCode("400");
                return userNoteResponse;
            }

            // Check if the note for the given mobile number exists
            Optional<UserNote> existingNoteOpt = userNoteRepository.findByMobileNo(request.getMobileNo());
            UserNote note;

            // If note exists, update it; otherwise, create a new note
            if (existingNoteOpt.isPresent()) {
                note = existingNoteOpt.get();
                note.setTitle(noteRequest.getTitle());
                note.setContent(noteRequest.getContent());
                note.setCreatedDate(LocalDate.now());
                userNoteResponse.setMessage("Note updated successfully");
                userNoteResponse.setResponseCode("200");
            } else {
                note = new UserNote();
                note.setMobileNo(request.getMobileNo());
                note.setTitle(noteRequest.getTitle());
                note.setContent(noteRequest.getContent());
                note.setUserNoteId(RandomStringGenerator.generateUserId(6));
                note.setCreatedDate(LocalDate.now());
                userNoteResponse.setMessage("Note created successfully");
                userNoteResponse.setResponseCode("201");
            }

            // Save the note to the repository
            userNoteRepository.save(note);
            userNoteResponse.setUserNoteId(note.getUserNoteId());  // Set the primary key of the saved entity
            userNoteResponse.setMobileNo(String.valueOf(request.getMobileNo()));
            userNoteResponse.setNote(noteRequest);  // Set the current note in the response
        }

        return userNoteResponse;
    }

    public UserNoteResponse getNoteByMobileNo(long mobileNo) {
        UserNoteResponse userNoteResponse = new UserNoteResponse();

        // Validate input
        if (mobileNo == 0) {
            userNoteResponse.setMessage("Invalid mobile number");
            userNoteResponse.setResponseCode("400");
            return userNoteResponse;
        }

        Optional<UserNote> note = userNoteRepository.findByMobileNo(mobileNo);

        if (note.isPresent()) {
            UserNote foundNote = note.get();
            Note noteDto = new Note();  // Create a DTO object to hold title and content
            noteDto.setTitle(foundNote.getTitle());
            noteDto.setContent(foundNote.getContent());
            noteDto.setCreatedDate(foundNote.getCreatedDate()); 

            userNoteResponse.setUserNoteId(foundNote.getUserNoteId());
            userNoteResponse.setMobileNo(String.valueOf(foundNote.getMobileNo()));
            userNoteResponse.setNote(noteDto);
            userNoteResponse.setResponseCode("200");
        } else {
            userNoteResponse.setMessage("Note not found for this mobile number");
            userNoteResponse.setResponseCode("404");
        }

        return userNoteResponse;
    }

    public UserNoteResponse deleteNoteByMobileNo(long mobileNo) {
        UserNoteResponse userNoteResponse = new UserNoteResponse();

        if (mobileNo == 0) {
            userNoteResponse.setMessage("Invalid mobile number");
            userNoteResponse.setResponseCode("400");
            return userNoteResponse;
        }

        Optional<UserNote> note = userNoteRepository.findByMobileNo(mobileNo);

        if (note.isPresent()) {
            userNoteRepository.deleteByMobileNo(mobileNo);
            userNoteResponse.setMessage("Note deleted successfully");
            userNoteResponse.setResponseCode("200");
        } else {
            userNoteResponse.setMessage("Note not found for this mobile number");
            userNoteResponse.setResponseCode("404");
        }

        return userNoteResponse;
    }
}
