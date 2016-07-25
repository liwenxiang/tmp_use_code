package com.huawei.seq.table;

import java.util.ArrayList;
import java.util.List;

public class Person {
	private String name;
	private String sex;

	private boolean mIsFake;
	
	public Person(String mName, String sex) {
		this(mName, sex, false);
	}

	public Person(String mName, String sex, boolean isFake) {
		super();
		this.name = mName;
		this.sex = sex;
		this.mIsFake = isFake;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public boolean ismIsFake() {
		return mIsFake;
	}

	public void setmIsFake(boolean mIsFake) {
		this.mIsFake = mIsFake;
	}
	

}

class DataModel {
	private List<Person> persons;
	
	public final static String[] SEX_KEYS = {"未知", "男", "女"};
	
	public int GetSexId(String sex) {
		for(int i = 0; i < SEX_KEYS.length; i++) {
			if (sex == SEX_KEYS[i]) {
				return i;
			}
		}
		//TODO  ERROR handle
		return 0;
	}
	public String GetSexByIndex(int index) {
		if (index < 0 || index >= SEX_KEYS.length) {
			//TODO  ERROR handle
			return SEX_KEYS[0];
		}
		return SEX_KEYS[index];
	}

    public DataModel(int samples) {
        persons = new ArrayList<Person>();
        for(int i=0; i<samples; i++) {
        	Person p = new Person("name" + i, SEX_KEYS[1]);
            persons.add(p);
        }
        AddFakePerson();
    }

    List<Person> getData()
    {
        return persons;
    }
    
    private Person GetFakePerson() {
        Person p = new Person("", SEX_KEYS[0], true);
        return p;
    }
    
    public void AddFakePerson() {
    	persons.add(GetFakePerson());
    }
}
