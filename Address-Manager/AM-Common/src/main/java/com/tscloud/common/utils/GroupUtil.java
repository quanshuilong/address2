package com.tscloud.common.utils;

import com.tscloud.common.framework.domain.ParentGroup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by shm on 2015/4/2.
 */
public class GroupUtil<E extends ParentGroup> {
    /**
     * 根据父节点的ID获取所有子节点的String 集合
     * @param list 分类表
     * @param pId 传入的父节点ID
     * @return String
     */

    private List<E> returnList = new ArrayList<E>();

    public List<E> getChildNodes(List<E> list, String pId) {
        if(list.size() == 0 && pId == "")
            return null;
        for (Iterator<E> iterator = list.iterator(); iterator.hasNext();) {
            E node = (E) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (node.getParentId().equals("0") && pId.equals(node.getId())) {
                recursionFn(list, node);
            }
            // 二、遍历所有的父节点下的所有子节点
            /*if (node.getParentId()==0) {
                recursionFn(list, node);
            }*/
        }
        return returnList;
    }

    public List<E> getChildNodes2(List<E> list, String pId) {
        if(list.size() == 0 && pId == "")
            return null;
        for (Iterator<E> iterator = list.iterator(); iterator.hasNext();) {
            E node = (E) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (pId.equals(node.getId())) {
                recursionFn(list, node);
            }
            // 二、遍历所有的父节点下的所有子节点
            /*if (node.getParentId()==0) {
                recursionFn(list, node);
            }*/
        }
        return returnList;
    }

    private void recursionFn(List<E> list, E node) {
        List<E> childList = getChildList(list, node);// 得到子节点列表
        if (hasChild(list, node)) {// 判断是否有子节点
            returnList.add(node);
            Iterator<E> it = childList.iterator();
            while (it.hasNext()) {
                E n = (E) it.next();
                recursionFn(list, n);
            }
        } else {
            returnList.add(node);
        }
    }

    // 得到子节点列表
    private List<E> getChildList(List<E> list, E node) {
        List<E> nodeList = new ArrayList<E>();
        Iterator<E> it = list.iterator();
        while (it.hasNext()) {
            E n = (E) it.next();
            if (n.getParentId().equals(node.getId())) {
                nodeList.add(n);
            }
        }
        return nodeList;
    }

    // 判断是否有子节点
    private boolean hasChild(List<E> list, E node) {
        return getChildList(list, node).size() > 0 ? true : false;
    }
}
