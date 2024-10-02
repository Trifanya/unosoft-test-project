package dev.trifanya;

import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

public class GroupManager {

    /**
     * Данный метод генерирует вспомогательную мапу повторов, затем с помощью
     * полученной мапы повторов формирует множество однострочных групп, после чего
     * с помощью той же мапы повторов формирует множество многострочных групп, затем
     * объединяет два множества и возвращает множество всех групп.
     * @param allLines - список строк, из которых надо сформировать группы
     * @return - возвращает список групп
     */
    public static Set<Group> formGroups(Set<String[]> allLines) {
        Map<Element, List<String[]>> repeats = generateRepeatMap(allLines);
        Set<Group> groups = new HashSet<>(formSinglelineGroups(allLines, repeats));
        repeats = repeats.entrySet().stream()
                .filter(entry -> entry.getValue().size() != 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        groups.addAll(formMultilineGroups(repeats));
        return groups;
    }

    /**
     * Данный метод генерирует мапу, в которой в роли ключа выступает элемент со
     * своим индексом ("элемент"_x, где x - индекс элемента в строке), а в роли значения -
     * список строк, в которых данный элемент встречается под тем же индексом.
     *
     * @param allLines - строки, по которым нужно выполнить генерацию мапы повторов
     * @return - возвращает мапу повторов
     */
    private static Map<Element, List<String[]>> generateRepeatMap(Set<String[]> allLines) {
        Map<Element, List<String[]>> elementRepeats = new HashMap<>();
        for (String[] line : allLines) {
            for (int i = 0; i < line.length; i++) {
                if (!line[i].isEmpty()) {
                    Element element = new Element(line[i], i);
                    if (elementRepeats.containsKey(element)) {
                        elementRepeats.get(element).add(line);
                    } else {
                        elementRepeats.put(element, new ArrayList<>(Collections.singletonList(line)));
                    }
                }
            }
        }
        return elementRepeats;
    }

    /**
     * Данный метод проходится по всем переданным строкам и с помощью мапы повторов
     * определяет строки, которые состоят только из уникальных элементов. Уникальным
     * элементам является тот, который встречается только в одной из переданных строк.
     * Если вся строка состоит из уникальных элементов, значит, она гарантированно
     * будет единственной в группе.
     *
     * @param allLines       - строки, из которых нужно сформировать однострочные группы
     * @param elementRepeats - мапа повторов
     * @return - возвращает множество всех возможных однострочных групп из allLines
     */
    private static Set<Group> formSinglelineGroups(Set<String[]> allLines, Map<Element, List<String[]>> elementRepeats) {
        Set<Group> singlelineGroups = new HashSet<>();
        for (String[] line : allLines) {
            boolean uniqueElementsOnly = true;
            for (int i = 0; i < line.length; i++) {
                Element curElement = new Element(line[i], i);
                if (!curElement.getValue().isEmpty() && elementRepeats.get(curElement).size() != 1) {
                    uniqueElementsOnly = false;
                    break;
                }
            }
            if (uniqueElementsOnly) {
                singlelineGroups.add(Group.of(line));
                for (int i = 0; i < line.length; i++) {
                    elementRepeats.remove(new Element(line[i], i));
                }
            }
        }
        return singlelineGroups;
    }

    /**
     * Данный метод проходится по мапе повторов и для каждого элемента сначала
     * формирует группу из строк, в которых этот элемент встречается в той же позиции,
     * затем для каждой из только что упомянутых строк, с помощью рекурсивного метода getLines()
     * возвращает строки, которые должны содержаться в одной группе с уже добавленными.
     *
     * @param elementRepeats - мапа повторов, в которой остались только элементы,
     *                       встречающиеся в двух и более строках.
     * @return - возвращает множество всех возможных многострочных групп
     */
    private static Set<Group> formMultilineGroups(Map<Element, List<String[]>> elementRepeats) {
        Set<Group> multilineGroups = new HashSet<>();
        Set<Element> keySet = new HashSet<>(elementRepeats.keySet());
        for (Element key : keySet) {
            List<String[]> lineList = null;
            if (elementRepeats.containsKey(key)) {
                lineList = elementRepeats.get(key);
            }
            elementRepeats.remove(key);
            Group newGroup = new Group();
            if (lineList != null && !lineList.isEmpty()) {
                lineList.forEach(line -> {
                    newGroup.addLine(line);
                    getLines(line, elementRepeats).forEach(newGroup::addLine);
                });
                multilineGroups.add(newGroup);
            }
        }
        return multilineGroups;
    }

    /**
     * Данный метод для каждого элемента переданной строки ищет, в каких еще строках
     * этот элемент встречается в той же позиции, таким образом формируя список строк,
     * которые должны содержаться в одной группе с переданной.
     *
     * @param line           - обрабатываемая строка
     * @param elementRepeats - мапа повторов, в которой остались только элементы,
     *                       встречающиеся в двух и более строках.
     * @return - возвращает список строк, входящих в группу с переданной строкой
     */
    private static List<String[]> getLines(String[] line, Map<Element, List<String[]>> elementRepeats) {
        List<String[]> lines = new ArrayList<>();
        for (int i = 0; i < line.length; i++) {
            Element element = new Element(line[i], i);
            List<String[]> linesWithElement = elementRepeats.get(element);
            if (CollectionUtils.isNotEmpty(linesWithElement)) {
                elementRepeats.remove(element);
                linesWithElement.forEach(line1 -> {
                    lines.add(line1);
                    lines.addAll(getLines(line1, elementRepeats));
                });
            }
        }
        return lines;
    }


}
