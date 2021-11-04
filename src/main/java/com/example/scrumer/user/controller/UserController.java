package com.example.scrumer.user.controller;

import com.example.scrumer.chat.command.ChannelCommand;
import com.example.scrumer.user.command.UserCommand;
import com.example.scrumer.user.mapper.UserMapper;
import com.example.scrumer.user.service.useCase.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserUseCase userUseCase;

    @GetMapping
    public UserCommand getLoggedUser() {
        return userUseCase.findByEmail(this.getUserEmail())
                .map(UserMapper::toUserCommand)
                .orElseThrow(() -> new NotFoundException("Logged in user not found"));
    }

    private String getUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }

    @GetMapping("/channels")
    public List<ChannelCommand> getChannelById() {
        return userUseCase.getChannels(getUserEmail());
    }

//    @GetMapping("/private-messages")
//    public List<PrivateMessagesCommand> getPrivateMessagesById() {
//        return userUseCase.getPrivateMessages(getUserEmail());
//    }

    @GetMapping("/{id}")
    public UserCommand getById(@PathVariable Long id) {
        return UserMapper.toUserCommand(userUseCase.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id,
                           @AuthenticationPrincipal UsernamePasswordAuthenticationToken user) {
        userUseCase.deleteById(id);
    }
}
