package com.sj.core.utils.web.easyui;

import java.util.ArrayList;
import java.util.List;

/**
 * 主要是完成TREE构造的工具
 * @author tody 2017年4月25日21:10:44
 */
public class TreeBuilder {
	
	/**
	 * 两层循环实现建树 默认第一级别为空
	 * @param 
	 *       treeNodes tree数据列表
	 * @return
	 * 		TREE层级数据
	 */
    public static List<EzTreeNode> bulid(List<EzTreeNode> treeNodes) {  
        List<EzTreeNode> trees = new ArrayList<EzTreeNode>();  
        for (EzTreeNode treeNode : treeNodes) {
            if (" ".equals(treeNode.getPid())) {
                trees.add(treeNode);  
            }
            for (EzTreeNode it : treeNodes) {
                if (it.getPid() == treeNode.getId()) {  
                    if (treeNode.getChildren() == null) {  
                        treeNode.setChildren(new ArrayList<EzTreeNode>());  
                    }  
                    treeNode.getChildren().add(it);  
                }
            }
        }
        return trees;  
    }

    /**
     *  两层循环实现建树
     * @param 
     * 		treeNodes tree数据列表 
     * @param 
     * 		lev1NodeName 第一级父节点的公共值
     * @return
     * 		TREE层级数据
     */    
    public static List<EzTreeNode> bulid(List<EzTreeNode> treeNodes,String lev1NodeName) {  
        List<EzTreeNode> trees = new ArrayList<EzTreeNode>();  
        for (EzTreeNode treeNode : treeNodes) {
            if (" ".equals(treeNode.getPid())) {
                trees.add(treeNode);  
            }
            for (EzTreeNode it : treeNodes) {
                if (it.getPid() == treeNode.getId()) {  
                    if (treeNode.getChildren() == null) {  
                        treeNode.setChildren(new ArrayList<EzTreeNode>());  
                    }  
                    treeNode.getChildren().add(it);  
                }
            }
        }
        return trees;  
    }
  
    /** 
     * 使用递归方法建树 -默认第一级别为空
     * @param 
     * 		treeNodes  tree数据列表
     * @return 
     * 		TREE层级数据
     */  
    public static List<EzTreeNode> buildByRecursive(List<EzTreeNode> treeNodes) { 
        List<EzTreeNode> trees = new ArrayList<EzTreeNode>();
        for (EzTreeNode treeNode : treeNodes) {
            if (" ".equals(treeNode.getPid())) {
                trees.add(findChildren(treeNode,treeNodes));  
            }
        }
        return trees;  
    }
    

    /**
     *  使用递归方法建树 
     * @param 
     * 		treeNodes tree数据列表 
     * @param 
     * 		lev1NodeName 第一级父节点的公共值
     * @return
     * 		TREE层级数据
     */    
    public static List<EzTreeNode> buildByRecursive(List<EzTreeNode> treeNodes,String lev1NodeName) { 
        List<EzTreeNode> trees = new ArrayList<EzTreeNode>();
        for (EzTreeNode treeNode : treeNodes) {
            if (lev1NodeName.equals(treeNode.getPid())) {
                trees.add(findChildren(treeNode,treeNodes));  
            }
        }
        return trees;  
    }
    
  
    /** 
     * 递归查找子节点 
     * @param 
     * 		treeNodes 
     * @return 
     */  
    public static EzTreeNode findChildren(EzTreeNode treeNode,List<EzTreeNode> treeNodes) {  
        for (EzTreeNode it : treeNodes) {  
            if(treeNode.getId().equals(it.getPid())) {  
                if (treeNode.getChildren() == null) {  
                    treeNode.setChildren(new ArrayList<EzTreeNode>());  
                }  
                treeNode.getChildren().add(findChildren(it,treeNodes));  
            }  
        }  
        return treeNode;  
    }  
  
  
  
    public static void main(String[] args) {  
    	EzTreeNode EzTreeNode1 =new EzTreeNode();
    	EzTreeNode1.setId("SYS01");
    	EzTreeNode1.setPid("SYS");
    	EzTreeNode1.setText("111");
    	
    	EzTreeNode EzTreeNode2 =new EzTreeNode();
    	EzTreeNode2.setId("SYS0101");
    	EzTreeNode2.setPid("SYS01");
    	EzTreeNode2.setText("2222");
    	
    	EzTreeNode EzTreeNode3 =new EzTreeNode();
    	EzTreeNode3.setId("SYS0102");
    	EzTreeNode3.setPid("SYS01");
    	EzTreeNode3.setText("222");
    	
    	List<EzTreeNode> trees = new ArrayList<EzTreeNode>();  
    	trees.add(EzTreeNode1);
    	trees.add(EzTreeNode2);
    	trees.add(EzTreeNode3);
//      List<EzTreeNode> trees2 = TreeBuilder.bulid(trees);  
//     System.out.println(trees2.toString());
     
//     List<EzTreeNode> trees_ = TreeBuilder.buildByRecursive(trees);  
     List<EzTreeNode> trees_2 = TreeBuilder.buildByRecursive(trees,"SYS");  
   System.out.println(trees_2.toString());
     
     
//        TreeNode treeNode1 = new TreeNode("1","广州","0");  
//        TreeNode treeNode2 = new TreeNode("2","深圳","0"); 
//        
//        TreeNode treeNode3 = new TreeNode("3","深圳","1");  
//        TreeNode treeNode4 = new TreeNode("4","深圳","1");  
//        
//        TreeNode treeNode5 = new TreeNode("5","深圳","2");  
//        TreeNode treeNode6 = new TreeNode("6","深圳","2");  
//  
//        
//        TreeNode treeNode7 = new TreeNode("7","深圳","3");  
//        TreeNode treeNode8 = new TreeNode("8","深圳","3"); 
//        

        

//        TreeNode treeNode3 = new TreeNode("3","天河区",treeNode1);  
//        TreeNode treeNode4 = new TreeNode("4","越秀区",treeNode1);  
//        TreeNode treeNode5 = new TreeNode("5","黄埔区",treeNode1);  
//        TreeNode treeNode6 = new TreeNode("6","石牌",treeNode3);  
//        TreeNode treeNode7 = new TreeNode("7","百脑汇",treeNode6);  
//  
//  
//        TreeNode treeNode8 = new TreeNode("8","南山区",treeNode2);  
//        TreeNode treeNode9 = new TreeNode("9","宝安区",treeNode2);  
//        TreeNode treeNode10 = new TreeNode("10","科技园",treeNode8);  
  
//  
//        List<TreeNode> list = new ArrayList<TreeNode>();  
//  
//        list.add(treeNode1);  
//        list.add(treeNode2);  
//        list.add(treeNode3);  
//        list.add(treeNode4);  
//        list.add(treeNode5);  
//        list.add(treeNode6);  
//        list.add(treeNode7);  
//        list.add(treeNode8);  

  
//        List<TreeNode> trees = TreeBuilder.bulid(list);  
//       System.out.println(trees.toString());
//  
//        List<TreeNode> trees_ = TreeBuilder.buildByRecursive(list);  
//        System.out.println(trees_.toString());
  
    }  
}
