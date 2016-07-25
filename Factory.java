package com.huawei.seq.table;

interface ReadTool {
	public String Read();
}

class ReadMem implements ReadTool {
	@Override
	public String Read() {
		return "abc";
	}
	
}
class ReadFile implements ReadTool {

	@Override
	public String Read() {
		return "def";
	}
}
class ReadFileCache implements ReadTool {

	@Override
	public String Read() {
		return "def";
	}
}

public class Factory {
	public static ReadTool GetReadTool(String name) {
		if (name == "ReadMem") {
			return new ReadMem();
		} else if (name == "ReadFile") {
			return new ReadFile();
		} else if (name == "ReadFileCache") {
			return new ReadFileCache();
		}
		return null;
	}
}


class UserTool {
	public void Init(String name) {
		ReadTool tool = Factory.GetReadTool(name);
		String content = tool.Read();
	}
}
