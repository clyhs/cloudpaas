package com.cloudpaas.common.utils;

import java.util.ArrayList;
import java.util.List;

import com.cloudpaas.common.vo.TreeVo;

/**
 * 
 * @author 大鱼
 *
 * @date 2019年8月13日 下午3:02:59
 */
public class TreeUtil {
	/**
	 * 两层循环实现建树
	 * 
	 * @param treeNodes
	 *            传入的树节点列表
	 * @return
	 */
	public static <T extends TreeVo> List<T> bulid(List<T> treeNodes, Object root) {

		List<T> trees = new ArrayList<T>();

		for (T treeNode : treeNodes) {

			if (root.equals(treeNode.getPId())) {
				trees.add(treeNode);
			}

			for (T it : treeNodes) {
				if (it.getPId() == treeNode.getId()) {
					if (treeNode.getChildrens() == null) {
						treeNode.setChildrens(new ArrayList<TreeVo>());
					}
					treeNode.add(it);
				}
			}
		}
		return trees;
	}

	/**
	 * 使用递归方法建树
	 * 
	 * @param treeNodes
	 * @return
	 */
	public static <T extends TreeVo> List<T> buildByRecursive(List<T> treeNodes, Object root) {
		List<T> trees = new ArrayList<T>();
		for (T treeNode : treeNodes) {
			if (root.equals(treeNode.getPId())) {
				trees.add(findChildren(treeNode, treeNodes));
			}
		}
		return trees;
	}

	/**
	 * 递归查找子节点
	 * 
	 * @param treeNodes
	 * @return
	 */
	public static <T extends TreeVo> T findChildren(T treeNode, List<T> treeNodes) {
		for (T it : treeNodes) {
			if (treeNode.getId() == it.getPId()) {
				if (treeNode.getChildrens() == null) {
					treeNode.setChildrens(new ArrayList<TreeVo>());
				}
				treeNode.add(findChildren(it, treeNodes));
			}
		}
		return treeNode;
	}

}
