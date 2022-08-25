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

    private void quickSort(List<String> integersArray, int leftBorder, int rightBorder) {
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        String pivot = integersArray.get((leftMarker + rightMarker) / 2);
        do {
            if(typeSort.equals("ask")){
                while (integersArray.get(leftMarker).compareTo(pivot) < 0) {
                    leftMarker++;
                }
                while (integersArray.get(rightMarker).compareTo(pivot) > 0) {
                    rightMarker--;
                }
            }else if(typeSort.equals("desc")){
                while (integersArray.get(leftMarker).compareTo(pivot) > 0) {
                    leftMarker++;
                }
                while (integersArray.get(rightMarker).compareTo(pivot) < 0) {
                    rightMarker--;
                }
            }
            if (leftMarker <= rightMarker) {
                if (leftMarker < rightMarker) {
                    String tmp = integersArray.get(leftMarker);
                    Collections.swap(integersArray, leftMarker, rightMarker);
                    integersArray.set(rightMarker, tmp);
                }
                leftMarker++;
                rightMarker--;
            }
        } while (leftMarker <= rightMarker);

        checkIf(integersArray, leftMarker, rightMarker, leftBorder, rightBorder);
    }

    private void checkIf(List<String> integersArray, int leftMarker, int rightMarker, int leftBorder, int rightBorder){
        if (leftMarker < rightBorder) {
            quickSort(integersArray, leftMarker, rightBorder);
        }
        if (leftBorder < rightMarker) {
            quickSort(integersArray, leftBorder, rightMarker);
        }

        if (integersArray.size() == 0){
            return ;
        }

        if (leftBorder >= rightMarker){
            this.integersArray = integersArray;
        }
    }

    private List<String> reverseSort(List<String> integersArray) {
        for (int i = 0; i < (integersArray.size() / 2); i++) {
            String temp = integersArray.get(i);
            Collections.swap(integersArray, i, integersArray.size() - i - 1);
            integersArray.set(integersArray.size() - i - 1, temp);
        }
        return integersArray;
    }

    @Override
    public List<String> getArrayList() {
        List<String> returnArray;
        switch (typeSort){
            case "ask":
            case "desc":
                int low = 0;
                int high = integersArray.size() - 1;
                quickSort(integersArray, low, high);
                returnArray = integersArray;
                break;
            case "reverse":
                returnArray = reverseSort(integersArray);
                break;
            default:
                System.out.println("Error" + typeSort);
                throw new IllegalStateException("Unexpected value: " + typeSort);
        }
        return returnArray;
    }

    @Override
    public void setArrayList(List<String> integersArray) {
        this.integersArray = integersArray;
    }
}
