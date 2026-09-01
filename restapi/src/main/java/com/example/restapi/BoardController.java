package com.example.restapi;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BoardController {

    private List<Board> x = new ArrayList<>();

    public BoardController() {
        x.add(new Board(1, "Tugboat", "Landyachtz", 199));
        x.add(new Board(2, "Dinghy", "Landyachtz", 160));
        x.add(new Board(3, "Bhangra", "Loaded", 320));
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
   
    @GetMapping("/hello/{name}")
    public String world(@PathVariable String name){
        return "hello " + name;
    }

    @GetMapping("/boards")
    public List<Board> getAllBoards(
        @RequestParam(required = false) String brand,
        @RequestParam(required = false) Integer maxPrice){

            if(brand == null && maxPrice == null){
                return x;
            }

            List<Board> gefiltert = new ArrayList<>();

            for(Board y : x){
                boolean bulBrand = (brand == null || y.getBrand().equalsIgnoreCase(brand));
                boolean bulPrice = (maxPrice == null || y.getPrice() <= maxPrice);
                if(bulBrand && bulPrice) gefiltert.add(y);
            }
            return gefiltert;
        }
        
        
    

    @GetMapping("/boards/{id}")
    public ResponseEntity<Board> getById(@PathVariable int id){
        for (Board i:x){
            if(i.getId() == id) return ResponseEntity.ok(i);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping("/boards")
    public Board createBoard(@RequestBody Board newBoard){
        x.add(newBoard);
        return newBoard;
    }

    @DeleteMapping("/boards/{id}")
    public ResponseEntity<Board> deleteBoard(@PathVariable int id) {
        boolean entfernt = x.removeIf(board -> board.getId() == id);
        if(entfernt) return ResponseEntity.noContent().build();
        else return ResponseEntity.notFound().build();
    }
}
