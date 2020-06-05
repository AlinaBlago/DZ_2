package ua.com.alevel.nix.experienceusingclass.builder;

import ua.com.alevel.nix.experienceusingclass.util.BundleUtil;

import java.util.Map;

public interface AbstractBuilder {

    default Map<String, String> getKeyValueMapByBundleProperties(String lang) {
        return BundleUtil.getBundleProperties(lang);
    }

    void buildDataList();
}
