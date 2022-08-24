package baldens.halliburton.config.service;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service("myListService")
public class MyListImpl implements MyList{
    private List<String> integersArray;
    private String typeSort;

    @Override
    public void setTypeSort(String typeSort){
        this.typeSort = typeSort;
    }

    private List<String> quickSort(List<String> integersArray) {
        for (int left = 0; left < integersArray.size(); left++) {
            String value = integersArray.get(left);

            int i = left - 1;
            for (; i >= 0; i--) {
                if(typeSort.equals("ask")){
                    if (value.length() < integersArray.get(i).length()) {
                        Collections.swap(integersArray, i + 1, i);
//                        integersArray[i + 1] = integersArray[i];
                    } else {
                        break;
                    }
                }else if(typeSort.equals("desc")){
                    if (value.length() > integersArray.get(i).length()) {
                        Collections.swap(integersArray, i + 1, i);
//                        integersArray[i + 1] = integersArray[i];
                    } else {
                        break;
                    }
                }
            }
            integersArray.set(i + 1, value);

        }
        return integersArray;
    }

    private List<String> reverseSort(List<String> integersArray) {
        for (int i = integersArray.size() - 1; i/2 > 0; i--) {
            String temp = integersArray.get(integersArray.size() - 1 - i);
            Collections.swap(integersArray, integersArray.size() - i - 1, i);
//            integersArray[integersArray.length - i - 1] = integersArray[i];
            integersArray.set(i, temp);
//            integersArray[i] = temp;
        }
        return integersArray;
    }

    @Override
    public List<String> getArrayList() {
        List<String> returnArray;
        switch (typeSort){
            case "ask":
            case "desc":
                returnArray = quickSort(integersArray);
                break;
            case "reverse":
                returnArray = reverseSort(integersArray);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + typeSort);
        }
        return returnArray;
    }

    @Override
    public void setArrayList(List<String> integersArray) {
        this.integersArray = integersArray;
    }
}
