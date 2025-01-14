package com.Erp_System.controller;



import com.Erp_System.dto.UserDto;
import com.Erp_System.response.ImageResponse;
import com.Erp_System.response.PaegableResponse;
import com.Erp_System.response.Utils;
import com.Erp_System.service.FileService;
import com.Erp_System.service.UserService;
import jakarta.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @Value("${user.profile.image.path}")
    private String imageUploadPath;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUsers(@RequestBody UserDto user){
        UserDto user1 = userService.createUser(user);
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUsers(@PathVariable int userId,@RequestBody UserDto user){
        UserDto updateUser = userService.updateUser(userId, user);
        return new ResponseEntity<>(updateUser,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<PaegableResponse<UserDto>> getAllUser(
            @RequestParam( value = "pageNumber",defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "20",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "name",required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir
    ){
        PaegableResponse<UserDto> allUser = userService.getAllUser(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(allUser,HttpStatus.OK);
    }

    @GetMapping("/single/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable int userId){
        UserDto userById = userService.findUserById(userId);
        return new ResponseEntity<>(userById,HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Utils> deleteUser(@PathVariable int userId){
        userService.deleteUser(userId);
        Utils userDeleted = Utils.builder().message("User is deleted").date(LocalDate.now()).status(HttpStatus.OK).build();
        return new ResponseEntity<>(userDeleted,HttpStatus.OK);
    }

    @PostMapping("/image/{userId}")
    public ResponseEntity<ImageResponse> uploadUserImage(@RequestParam("userImage") MultipartFile image, @PathVariable int userId) throws IOException {
        String imageName = fileService.uploadFile(image, imageUploadPath);
        UserDto user = userService.findUserById(userId);
        user.setImageName(imageName);
        UserDto userDto = userService.updateUser(userId,user);
        ImageResponse imageResponse = ImageResponse.builder().imageName(imageName).success(true).message("image is uploaded successfully ").status(HttpStatus.CREATED).build();
        return new ResponseEntity<>(imageResponse, HttpStatus.CREATED);

    }

    //serve user image

    @GetMapping(value = "/image/{userId}")
    public void serveUserImage(@PathVariable int userId, HttpServletResponse response) throws IOException {
        UserDto user = userService.findUserById(userId);
        log.info("User image name : {} ", user.getImageName());
        InputStream resource = fileService.getResource(imageUploadPath, user.getImageName());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());

    }
}
