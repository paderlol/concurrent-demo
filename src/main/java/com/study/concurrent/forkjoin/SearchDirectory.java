/**
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: bubugao </p>
 */
package com.study.concurrent.forkjoin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Description: TODO {递归查询各个目录下查询某个关键字}<br/>
 * 
 * @author zhanglong
 * @date: 2015年7月7日 上午10:00:00
 * @version 1.0
 * @since JDK 1.7
 */
public class SearchDirectory extends RecursiveTask<List<String>> {

    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     */
    private static final long serialVersionUID = 1L;
    /**
     * 搜索目录
     */
    private String directoryName;
    /**
     * 查询关键字
     */
    private String searchName;

    public SearchDirectory(String directoryName, String searchName) {
        super();
        this.directoryName = directoryName;
        this.searchName = searchName;
    }

    @Override
    protected List<String> compute() {
        List<String> matchingFilesList = new ArrayList<String>();
        List<SearchDirectory> taskList = new ArrayList<SearchDirectory>();
        File directory = new File(directoryName);
        if (directory == null || "".equals(directory) || !directory.exists()) {
            throw new IllegalArgumentException("directory name is not vaild");
        }
        File[] listFiles = directory.listFiles();
        for (File file : listFiles) {
            if (file.isDirectory()) { //如果是目录则进行
                SearchDirectory searchDirectory = new SearchDirectory(file.getPath(), searchName);
                searchDirectory.fork();
                taskList.add(searchDirectory);
            } else {
                if (checkName(file.getName())) {
                    matchingFilesList.add(file.getName());
                }
            }
        }
        for (SearchDirectory searchDirectory : taskList) {
            List<String> matching = searchDirectory.join();
            matchingFilesList.addAll(matching);

        }
        return matchingFilesList;
    }

    private boolean checkName(String path) {
        // TODO Auto-generated method stub
        return path.contains(searchName);
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        SearchDirectory searchDir = new SearchDirectory("E:/工作", "张龙");
        pool.execute(searchDir);
        List<String> result = searchDir.join();
        System.out.println(result);

    }

}
