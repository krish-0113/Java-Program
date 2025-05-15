@RestController
public class FileUploadController {

    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        // System.out.println(file.getOriginalFilename());
        // System.out.println(file.getSize());
        // System.out.println(file.getContentType());
        // System.out.println(file.getContentType());
        // System.out.println(file.getName());


//validation
        if(file.isEmpty()){
            return ResponseEntity.status(httpStatus.INTERNAL_SERVER_ERROR).body("Request must contains file");

        }

        if()

        return ResponseEntity.ok("It's working");
        
    }
}
