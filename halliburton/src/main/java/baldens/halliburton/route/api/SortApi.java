package baldens.halliburton.route.api;

import baldens.halliburton.config.service.MyList;
import baldens.halliburton.model.http.TextSort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
public class SortApi {
    private final MyList myList;

    public SortApi(@Qualifier("myListService") MyList myList){
        this.myList = myList;
    }

    @RequestMapping(value = "/sort", method = RequestMethod.POST)
    public TextSort sortForType(@RequestBody TextSort textSort){
        System.out.println(textSort.typeSort);

        myList.setTypeSort(textSort.typeSort);
        List<String> listWords = Arrays.stream(textSort.text.split(" ")).collect(Collectors.toList());
        myList.setArrayList(listWords);

        StringBuilder resultSort = new StringBuilder();
        for(String str  : myList.getArrayList()){
            resultSort.append(str).append(" ");
        }
        textSort.setText(resultSort.toString());

        return textSort;
    }
}