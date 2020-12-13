package ui.pl.allegro.interfaces;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public interface CategoryNavigator {

    <T extends Enum<T>> T getParentCategory();
    String getCategoryTitle();

    static List<CategoryNavigator> getFullCategoriesChain(CategoryNavigator category) {
        List<CategoryNavigator> categories = new LinkedList<>();
        while (null != category) {
            categories.add(category);
            category = category.getParentCategory();
        }
        Collections.reverse(categories);
        return categories;
    }

    static CategoryNavigator getTopLevelCategory(CategoryNavigator category) {
        return getFullCategoriesChain(category).get(0);
    }

    static CategoryNavigator getTopLevelSubCategory(CategoryNavigator category) {
        return getFullCategoriesChain(category).get(1);
    }

    static List<CategoryNavigator> getSubCategoriesChain(CategoryNavigator category) {
        List<CategoryNavigator> allCategories = getFullCategoriesChain(category);
        return allCategories.subList(2, allCategories.size());
    }

}
