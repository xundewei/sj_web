package com.sj.web.model.system;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SysMenuExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysMenuExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andPkSysMenuIsNull() {
            addCriterion("PK_SYS_MENU is null");
            return (Criteria) this;
        }

        public Criteria andPkSysMenuIsNotNull() {
            addCriterion("PK_SYS_MENU is not null");
            return (Criteria) this;
        }

        public Criteria andPkSysMenuEqualTo(String value) {
            addCriterion("PK_SYS_MENU =", value, "pkSysMenu");
            return (Criteria) this;
        }

        public Criteria andPkSysMenuNotEqualTo(String value) {
            addCriterion("PK_SYS_MENU <>", value, "pkSysMenu");
            return (Criteria) this;
        }

        public Criteria andPkSysMenuGreaterThan(String value) {
            addCriterion("PK_SYS_MENU >", value, "pkSysMenu");
            return (Criteria) this;
        }

        public Criteria andPkSysMenuGreaterThanOrEqualTo(String value) {
            addCriterion("PK_SYS_MENU >=", value, "pkSysMenu");
            return (Criteria) this;
        }

        public Criteria andPkSysMenuLessThan(String value) {
            addCriterion("PK_SYS_MENU <", value, "pkSysMenu");
            return (Criteria) this;
        }

        public Criteria andPkSysMenuLessThanOrEqualTo(String value) {
            addCriterion("PK_SYS_MENU <=", value, "pkSysMenu");
            return (Criteria) this;
        }

        public Criteria andPkSysMenuLike(String value) {
            addCriterion("PK_SYS_MENU like", value, "pkSysMenu");
            return (Criteria) this;
        }

        public Criteria andPkSysMenuNotLike(String value) {
            addCriterion("PK_SYS_MENU not like", value, "pkSysMenu");
            return (Criteria) this;
        }

        public Criteria andPkSysMenuIn(List<String> values) {
            addCriterion("PK_SYS_MENU in", values, "pkSysMenu");
            return (Criteria) this;
        }

        public Criteria andPkSysMenuNotIn(List<String> values) {
            addCriterion("PK_SYS_MENU not in", values, "pkSysMenu");
            return (Criteria) this;
        }

        public Criteria andPkSysMenuBetween(String value1, String value2) {
            addCriterion("PK_SYS_MENU between", value1, value2, "pkSysMenu");
            return (Criteria) this;
        }

        public Criteria andPkSysMenuNotBetween(String value1, String value2) {
            addCriterion("PK_SYS_MENU not between", value1, value2, "pkSysMenu");
            return (Criteria) this;
        }

        public Criteria andMenucodeIsNull() {
            addCriterion("MENUCODE is null");
            return (Criteria) this;
        }

        public Criteria andMenucodeIsNotNull() {
            addCriterion("MENUCODE is not null");
            return (Criteria) this;
        }

        public Criteria andMenucodeEqualTo(String value) {
            addCriterion("MENUCODE =", value, "menucode");
            return (Criteria) this;
        }

        public Criteria andMenucodeNotEqualTo(String value) {
            addCriterion("MENUCODE <>", value, "menucode");
            return (Criteria) this;
        }

        public Criteria andMenucodeGreaterThan(String value) {
            addCriterion("MENUCODE >", value, "menucode");
            return (Criteria) this;
        }

        public Criteria andMenucodeGreaterThanOrEqualTo(String value) {
            addCriterion("MENUCODE >=", value, "menucode");
            return (Criteria) this;
        }

        public Criteria andMenucodeLessThan(String value) {
            addCriterion("MENUCODE <", value, "menucode");
            return (Criteria) this;
        }

        public Criteria andMenucodeLessThanOrEqualTo(String value) {
            addCriterion("MENUCODE <=", value, "menucode");
            return (Criteria) this;
        }

        public Criteria andMenucodeLike(String value) {
            addCriterion("MENUCODE like", value, "menucode");
            return (Criteria) this;
        }

        public Criteria andMenucodeNotLike(String value) {
            addCriterion("MENUCODE not like", value, "menucode");
            return (Criteria) this;
        }

        public Criteria andMenucodeIn(List<String> values) {
            addCriterion("MENUCODE in", values, "menucode");
            return (Criteria) this;
        }

        public Criteria andMenucodeNotIn(List<String> values) {
            addCriterion("MENUCODE not in", values, "menucode");
            return (Criteria) this;
        }

        public Criteria andMenucodeBetween(String value1, String value2) {
            addCriterion("MENUCODE between", value1, value2, "menucode");
            return (Criteria) this;
        }

        public Criteria andMenucodeNotBetween(String value1, String value2) {
            addCriterion("MENUCODE not between", value1, value2, "menucode");
            return (Criteria) this;
        }

        public Criteria andMenunameIsNull() {
            addCriterion("MENUNAME is null");
            return (Criteria) this;
        }

        public Criteria andMenunameIsNotNull() {
            addCriterion("MENUNAME is not null");
            return (Criteria) this;
        }

        public Criteria andMenunameEqualTo(String value) {
            addCriterion("MENUNAME =", value, "menuname");
            return (Criteria) this;
        }

        public Criteria andMenunameNotEqualTo(String value) {
            addCriterion("MENUNAME <>", value, "menuname");
            return (Criteria) this;
        }

        public Criteria andMenunameGreaterThan(String value) {
            addCriterion("MENUNAME >", value, "menuname");
            return (Criteria) this;
        }

        public Criteria andMenunameGreaterThanOrEqualTo(String value) {
            addCriterion("MENUNAME >=", value, "menuname");
            return (Criteria) this;
        }

        public Criteria andMenunameLessThan(String value) {
            addCriterion("MENUNAME <", value, "menuname");
            return (Criteria) this;
        }

        public Criteria andMenunameLessThanOrEqualTo(String value) {
            addCriterion("MENUNAME <=", value, "menuname");
            return (Criteria) this;
        }

        public Criteria andMenunameLike(String value) {
            addCriterion("MENUNAME like", value, "menuname");
            return (Criteria) this;
        }

        public Criteria andMenunameNotLike(String value) {
            addCriterion("MENUNAME not like", value, "menuname");
            return (Criteria) this;
        }

        public Criteria andMenunameIn(List<String> values) {
            addCriterion("MENUNAME in", values, "menuname");
            return (Criteria) this;
        }

        public Criteria andMenunameNotIn(List<String> values) {
            addCriterion("MENUNAME not in", values, "menuname");
            return (Criteria) this;
        }

        public Criteria andMenunameBetween(String value1, String value2) {
            addCriterion("MENUNAME between", value1, value2, "menuname");
            return (Criteria) this;
        }

        public Criteria andMenunameNotBetween(String value1, String value2) {
            addCriterion("MENUNAME not between", value1, value2, "menuname");
            return (Criteria) this;
        }

        public Criteria andDisplaynameIsNull() {
            addCriterion("DISPLAYNAME is null");
            return (Criteria) this;
        }

        public Criteria andDisplaynameIsNotNull() {
            addCriterion("DISPLAYNAME is not null");
            return (Criteria) this;
        }

        public Criteria andDisplaynameEqualTo(String value) {
            addCriterion("DISPLAYNAME =", value, "displayname");
            return (Criteria) this;
        }

        public Criteria andDisplaynameNotEqualTo(String value) {
            addCriterion("DISPLAYNAME <>", value, "displayname");
            return (Criteria) this;
        }

        public Criteria andDisplaynameGreaterThan(String value) {
            addCriterion("DISPLAYNAME >", value, "displayname");
            return (Criteria) this;
        }

        public Criteria andDisplaynameGreaterThanOrEqualTo(String value) {
            addCriterion("DISPLAYNAME >=", value, "displayname");
            return (Criteria) this;
        }

        public Criteria andDisplaynameLessThan(String value) {
            addCriterion("DISPLAYNAME <", value, "displayname");
            return (Criteria) this;
        }

        public Criteria andDisplaynameLessThanOrEqualTo(String value) {
            addCriterion("DISPLAYNAME <=", value, "displayname");
            return (Criteria) this;
        }

        public Criteria andDisplaynameLike(String value) {
            addCriterion("DISPLAYNAME like", value, "displayname");
            return (Criteria) this;
        }

        public Criteria andDisplaynameNotLike(String value) {
            addCriterion("DISPLAYNAME not like", value, "displayname");
            return (Criteria) this;
        }

        public Criteria andDisplaynameIn(List<String> values) {
            addCriterion("DISPLAYNAME in", values, "displayname");
            return (Criteria) this;
        }

        public Criteria andDisplaynameNotIn(List<String> values) {
            addCriterion("DISPLAYNAME not in", values, "displayname");
            return (Criteria) this;
        }

        public Criteria andDisplaynameBetween(String value1, String value2) {
            addCriterion("DISPLAYNAME between", value1, value2, "displayname");
            return (Criteria) this;
        }

        public Criteria andDisplaynameNotBetween(String value1, String value2) {
            addCriterion("DISPLAYNAME not between", value1, value2, "displayname");
            return (Criteria) this;
        }

        public Criteria andDisplayorderIsNull() {
            addCriterion("DISPLAYORDER is null");
            return (Criteria) this;
        }

        public Criteria andDisplayorderIsNotNull() {
            addCriterion("DISPLAYORDER is not null");
            return (Criteria) this;
        }

        public Criteria andDisplayorderEqualTo(BigDecimal value) {
            addCriterion("DISPLAYORDER =", value, "displayorder");
            return (Criteria) this;
        }

        public Criteria andDisplayorderNotEqualTo(BigDecimal value) {
            addCriterion("DISPLAYORDER <>", value, "displayorder");
            return (Criteria) this;
        }

        public Criteria andDisplayorderGreaterThan(BigDecimal value) {
            addCriterion("DISPLAYORDER >", value, "displayorder");
            return (Criteria) this;
        }

        public Criteria andDisplayorderGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("DISPLAYORDER >=", value, "displayorder");
            return (Criteria) this;
        }

        public Criteria andDisplayorderLessThan(BigDecimal value) {
            addCriterion("DISPLAYORDER <", value, "displayorder");
            return (Criteria) this;
        }

        public Criteria andDisplayorderLessThanOrEqualTo(BigDecimal value) {
            addCriterion("DISPLAYORDER <=", value, "displayorder");
            return (Criteria) this;
        }

        public Criteria andDisplayorderIn(List<BigDecimal> values) {
            addCriterion("DISPLAYORDER in", values, "displayorder");
            return (Criteria) this;
        }

        public Criteria andDisplayorderNotIn(List<BigDecimal> values) {
            addCriterion("DISPLAYORDER not in", values, "displayorder");
            return (Criteria) this;
        }

        public Criteria andDisplayorderBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DISPLAYORDER between", value1, value2, "displayorder");
            return (Criteria) this;
        }

        public Criteria andDisplayorderNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DISPLAYORDER not between", value1, value2, "displayorder");
            return (Criteria) this;
        }

        public Criteria andLevIsNull() {
            addCriterion("LEV is null");
            return (Criteria) this;
        }

        public Criteria andLevIsNotNull() {
            addCriterion("LEV is not null");
            return (Criteria) this;
        }

        public Criteria andLevEqualTo(BigDecimal value) {
            addCriterion("LEV =", value, "lev");
            return (Criteria) this;
        }

        public Criteria andLevNotEqualTo(BigDecimal value) {
            addCriterion("LEV <>", value, "lev");
            return (Criteria) this;
        }

        public Criteria andLevGreaterThan(BigDecimal value) {
            addCriterion("LEV >", value, "lev");
            return (Criteria) this;
        }

        public Criteria andLevGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("LEV >=", value, "lev");
            return (Criteria) this;
        }

        public Criteria andLevLessThan(BigDecimal value) {
            addCriterion("LEV <", value, "lev");
            return (Criteria) this;
        }

        public Criteria andLevLessThanOrEqualTo(BigDecimal value) {
            addCriterion("LEV <=", value, "lev");
            return (Criteria) this;
        }

        public Criteria andLevIn(List<BigDecimal> values) {
            addCriterion("LEV in", values, "lev");
            return (Criteria) this;
        }

        public Criteria andLevNotIn(List<BigDecimal> values) {
            addCriterion("LEV not in", values, "lev");
            return (Criteria) this;
        }

        public Criteria andLevBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LEV between", value1, value2, "lev");
            return (Criteria) this;
        }

        public Criteria andLevNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LEV not between", value1, value2, "lev");
            return (Criteria) this;
        }

        public Criteria andParentcodeIsNull() {
            addCriterion("PARENTCODE is null");
            return (Criteria) this;
        }

        public Criteria andParentcodeIsNotNull() {
            addCriterion("PARENTCODE is not null");
            return (Criteria) this;
        }

        public Criteria andParentcodeEqualTo(String value) {
            addCriterion("PARENTCODE =", value, "parentcode");
            return (Criteria) this;
        }

        public Criteria andParentcodeNotEqualTo(String value) {
            addCriterion("PARENTCODE <>", value, "parentcode");
            return (Criteria) this;
        }

        public Criteria andParentcodeGreaterThan(String value) {
            addCriterion("PARENTCODE >", value, "parentcode");
            return (Criteria) this;
        }

        public Criteria andParentcodeGreaterThanOrEqualTo(String value) {
            addCriterion("PARENTCODE >=", value, "parentcode");
            return (Criteria) this;
        }

        public Criteria andParentcodeLessThan(String value) {
            addCriterion("PARENTCODE <", value, "parentcode");
            return (Criteria) this;
        }

        public Criteria andParentcodeLessThanOrEqualTo(String value) {
            addCriterion("PARENTCODE <=", value, "parentcode");
            return (Criteria) this;
        }

        public Criteria andParentcodeLike(String value) {
            addCriterion("PARENTCODE like", value, "parentcode");
            return (Criteria) this;
        }

        public Criteria andParentcodeNotLike(String value) {
            addCriterion("PARENTCODE not like", value, "parentcode");
            return (Criteria) this;
        }

        public Criteria andParentcodeIn(List<String> values) {
            addCriterion("PARENTCODE in", values, "parentcode");
            return (Criteria) this;
        }

        public Criteria andParentcodeNotIn(List<String> values) {
            addCriterion("PARENTCODE not in", values, "parentcode");
            return (Criteria) this;
        }

        public Criteria andParentcodeBetween(String value1, String value2) {
            addCriterion("PARENTCODE between", value1, value2, "parentcode");
            return (Criteria) this;
        }

        public Criteria andParentcodeNotBetween(String value1, String value2) {
            addCriterion("PARENTCODE not between", value1, value2, "parentcode");
            return (Criteria) this;
        }

        public Criteria andIconIsNull() {
            addCriterion("ICON is null");
            return (Criteria) this;
        }

        public Criteria andIconIsNotNull() {
            addCriterion("ICON is not null");
            return (Criteria) this;
        }

        public Criteria andIconEqualTo(String value) {
            addCriterion("ICON =", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotEqualTo(String value) {
            addCriterion("ICON <>", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThan(String value) {
            addCriterion("ICON >", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThanOrEqualTo(String value) {
            addCriterion("ICON >=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThan(String value) {
            addCriterion("ICON <", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThanOrEqualTo(String value) {
            addCriterion("ICON <=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLike(String value) {
            addCriterion("ICON like", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotLike(String value) {
            addCriterion("ICON not like", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconIn(List<String> values) {
            addCriterion("ICON in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotIn(List<String> values) {
            addCriterion("ICON not in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconBetween(String value1, String value2) {
            addCriterion("ICON between", value1, value2, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotBetween(String value1, String value2) {
            addCriterion("ICON not between", value1, value2, "icon");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("URL is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("URL is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("URL =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("URL <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("URL >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("URL >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("URL <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("URL <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("URL like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("URL not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("URL in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("URL not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("URL between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("URL not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andEnableflagIsNull() {
            addCriterion("ENABLEFLAG is null");
            return (Criteria) this;
        }

        public Criteria andEnableflagIsNotNull() {
            addCriterion("ENABLEFLAG is not null");
            return (Criteria) this;
        }

        public Criteria andEnableflagEqualTo(String value) {
            addCriterion("ENABLEFLAG =", value, "enableflag");
            return (Criteria) this;
        }

        public Criteria andEnableflagNotEqualTo(String value) {
            addCriterion("ENABLEFLAG <>", value, "enableflag");
            return (Criteria) this;
        }

        public Criteria andEnableflagGreaterThan(String value) {
            addCriterion("ENABLEFLAG >", value, "enableflag");
            return (Criteria) this;
        }

        public Criteria andEnableflagGreaterThanOrEqualTo(String value) {
            addCriterion("ENABLEFLAG >=", value, "enableflag");
            return (Criteria) this;
        }

        public Criteria andEnableflagLessThan(String value) {
            addCriterion("ENABLEFLAG <", value, "enableflag");
            return (Criteria) this;
        }

        public Criteria andEnableflagLessThanOrEqualTo(String value) {
            addCriterion("ENABLEFLAG <=", value, "enableflag");
            return (Criteria) this;
        }

        public Criteria andEnableflagLike(String value) {
            addCriterion("ENABLEFLAG like", value, "enableflag");
            return (Criteria) this;
        }

        public Criteria andEnableflagNotLike(String value) {
            addCriterion("ENABLEFLAG not like", value, "enableflag");
            return (Criteria) this;
        }

        public Criteria andEnableflagIn(List<String> values) {
            addCriterion("ENABLEFLAG in", values, "enableflag");
            return (Criteria) this;
        }

        public Criteria andEnableflagNotIn(List<String> values) {
            addCriterion("ENABLEFLAG not in", values, "enableflag");
            return (Criteria) this;
        }

        public Criteria andEnableflagBetween(String value1, String value2) {
            addCriterion("ENABLEFLAG between", value1, value2, "enableflag");
            return (Criteria) this;
        }

        public Criteria andEnableflagNotBetween(String value1, String value2) {
            addCriterion("ENABLEFLAG not between", value1, value2, "enableflag");
            return (Criteria) this;
        }

        public Criteria andAdminflagIsNull() {
            addCriterion("ADMINFLAG is null");
            return (Criteria) this;
        }

        public Criteria andAdminflagIsNotNull() {
            addCriterion("ADMINFLAG is not null");
            return (Criteria) this;
        }

        public Criteria andAdminflagEqualTo(String value) {
            addCriterion("ADMINFLAG =", value, "adminflag");
            return (Criteria) this;
        }

        public Criteria andAdminflagNotEqualTo(String value) {
            addCriterion("ADMINFLAG <>", value, "adminflag");
            return (Criteria) this;
        }

        public Criteria andAdminflagGreaterThan(String value) {
            addCriterion("ADMINFLAG >", value, "adminflag");
            return (Criteria) this;
        }

        public Criteria andAdminflagGreaterThanOrEqualTo(String value) {
            addCriterion("ADMINFLAG >=", value, "adminflag");
            return (Criteria) this;
        }

        public Criteria andAdminflagLessThan(String value) {
            addCriterion("ADMINFLAG <", value, "adminflag");
            return (Criteria) this;
        }

        public Criteria andAdminflagLessThanOrEqualTo(String value) {
            addCriterion("ADMINFLAG <=", value, "adminflag");
            return (Criteria) this;
        }

        public Criteria andAdminflagLike(String value) {
            addCriterion("ADMINFLAG like", value, "adminflag");
            return (Criteria) this;
        }

        public Criteria andAdminflagNotLike(String value) {
            addCriterion("ADMINFLAG not like", value, "adminflag");
            return (Criteria) this;
        }

        public Criteria andAdminflagIn(List<String> values) {
            addCriterion("ADMINFLAG in", values, "adminflag");
            return (Criteria) this;
        }

        public Criteria andAdminflagNotIn(List<String> values) {
            addCriterion("ADMINFLAG not in", values, "adminflag");
            return (Criteria) this;
        }

        public Criteria andAdminflagBetween(String value1, String value2) {
            addCriterion("ADMINFLAG between", value1, value2, "adminflag");
            return (Criteria) this;
        }

        public Criteria andAdminflagNotBetween(String value1, String value2) {
            addCriterion("ADMINFLAG not between", value1, value2, "adminflag");
            return (Criteria) this;
        }

        public Criteria andMenutypeIsNull() {
            addCriterion("MENUTYPE is null");
            return (Criteria) this;
        }

        public Criteria andMenutypeIsNotNull() {
            addCriterion("MENUTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andMenutypeEqualTo(String value) {
            addCriterion("MENUTYPE =", value, "menutype");
            return (Criteria) this;
        }

        public Criteria andMenutypeNotEqualTo(String value) {
            addCriterion("MENUTYPE <>", value, "menutype");
            return (Criteria) this;
        }

        public Criteria andMenutypeGreaterThan(String value) {
            addCriterion("MENUTYPE >", value, "menutype");
            return (Criteria) this;
        }

        public Criteria andMenutypeGreaterThanOrEqualTo(String value) {
            addCriterion("MENUTYPE >=", value, "menutype");
            return (Criteria) this;
        }

        public Criteria andMenutypeLessThan(String value) {
            addCriterion("MENUTYPE <", value, "menutype");
            return (Criteria) this;
        }

        public Criteria andMenutypeLessThanOrEqualTo(String value) {
            addCriterion("MENUTYPE <=", value, "menutype");
            return (Criteria) this;
        }

        public Criteria andMenutypeLike(String value) {
            addCriterion("MENUTYPE like", value, "menutype");
            return (Criteria) this;
        }

        public Criteria andMenutypeNotLike(String value) {
            addCriterion("MENUTYPE not like", value, "menutype");
            return (Criteria) this;
        }

        public Criteria andMenutypeIn(List<String> values) {
            addCriterion("MENUTYPE in", values, "menutype");
            return (Criteria) this;
        }

        public Criteria andMenutypeNotIn(List<String> values) {
            addCriterion("MENUTYPE not in", values, "menutype");
            return (Criteria) this;
        }

        public Criteria andMenutypeBetween(String value1, String value2) {
            addCriterion("MENUTYPE between", value1, value2, "menutype");
            return (Criteria) this;
        }

        public Criteria andMenutypeNotBetween(String value1, String value2) {
            addCriterion("MENUTYPE not between", value1, value2, "menutype");
            return (Criteria) this;
        }

        public Criteria andRemakrIsNull() {
            addCriterion("REMAKR is null");
            return (Criteria) this;
        }

        public Criteria andRemakrIsNotNull() {
            addCriterion("REMAKR is not null");
            return (Criteria) this;
        }

        public Criteria andRemakrEqualTo(String value) {
            addCriterion("REMAKR =", value, "remakr");
            return (Criteria) this;
        }

        public Criteria andRemakrNotEqualTo(String value) {
            addCriterion("REMAKR <>", value, "remakr");
            return (Criteria) this;
        }

        public Criteria andRemakrGreaterThan(String value) {
            addCriterion("REMAKR >", value, "remakr");
            return (Criteria) this;
        }

        public Criteria andRemakrGreaterThanOrEqualTo(String value) {
            addCriterion("REMAKR >=", value, "remakr");
            return (Criteria) this;
        }

        public Criteria andRemakrLessThan(String value) {
            addCriterion("REMAKR <", value, "remakr");
            return (Criteria) this;
        }

        public Criteria andRemakrLessThanOrEqualTo(String value) {
            addCriterion("REMAKR <=", value, "remakr");
            return (Criteria) this;
        }

        public Criteria andRemakrLike(String value) {
            addCriterion("REMAKR like", value, "remakr");
            return (Criteria) this;
        }

        public Criteria andRemakrNotLike(String value) {
            addCriterion("REMAKR not like", value, "remakr");
            return (Criteria) this;
        }

        public Criteria andRemakrIn(List<String> values) {
            addCriterion("REMAKR in", values, "remakr");
            return (Criteria) this;
        }

        public Criteria andRemakrNotIn(List<String> values) {
            addCriterion("REMAKR not in", values, "remakr");
            return (Criteria) this;
        }

        public Criteria andRemakrBetween(String value1, String value2) {
            addCriterion("REMAKR between", value1, value2, "remakr");
            return (Criteria) this;
        }

        public Criteria andRemakrNotBetween(String value1, String value2) {
            addCriterion("REMAKR not between", value1, value2, "remakr");
            return (Criteria) this;
        }

        public Criteria andCreateuserIsNull() {
            addCriterion("CREATEUSER is null");
            return (Criteria) this;
        }

        public Criteria andCreateuserIsNotNull() {
            addCriterion("CREATEUSER is not null");
            return (Criteria) this;
        }

        public Criteria andCreateuserEqualTo(String value) {
            addCriterion("CREATEUSER =", value, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserNotEqualTo(String value) {
            addCriterion("CREATEUSER <>", value, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserGreaterThan(String value) {
            addCriterion("CREATEUSER >", value, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserGreaterThanOrEqualTo(String value) {
            addCriterion("CREATEUSER >=", value, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserLessThan(String value) {
            addCriterion("CREATEUSER <", value, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserLessThanOrEqualTo(String value) {
            addCriterion("CREATEUSER <=", value, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserLike(String value) {
            addCriterion("CREATEUSER like", value, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserNotLike(String value) {
            addCriterion("CREATEUSER not like", value, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserIn(List<String> values) {
            addCriterion("CREATEUSER in", values, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserNotIn(List<String> values) {
            addCriterion("CREATEUSER not in", values, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserBetween(String value1, String value2) {
            addCriterion("CREATEUSER between", value1, value2, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserNotBetween(String value1, String value2) {
            addCriterion("CREATEUSER not between", value1, value2, "createuser");
            return (Criteria) this;
        }

        public Criteria andTsIsNull() {
            addCriterion("TS is null");
            return (Criteria) this;
        }

        public Criteria andTsIsNotNull() {
            addCriterion("TS is not null");
            return (Criteria) this;
        }

        public Criteria andTsEqualTo(String value) {
            addCriterion("TS =", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsNotEqualTo(String value) {
            addCriterion("TS <>", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsGreaterThan(String value) {
            addCriterion("TS >", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsGreaterThanOrEqualTo(String value) {
            addCriterion("TS >=", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsLessThan(String value) {
            addCriterion("TS <", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsLessThanOrEqualTo(String value) {
            addCriterion("TS <=", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsLike(String value) {
            addCriterion("TS like", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsNotLike(String value) {
            addCriterion("TS not like", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsIn(List<String> values) {
            addCriterion("TS in", values, "ts");
            return (Criteria) this;
        }

        public Criteria andTsNotIn(List<String> values) {
            addCriterion("TS not in", values, "ts");
            return (Criteria) this;
        }

        public Criteria andTsBetween(String value1, String value2) {
            addCriterion("TS between", value1, value2, "ts");
            return (Criteria) this;
        }

        public Criteria andTsNotBetween(String value1, String value2) {
            addCriterion("TS not between", value1, value2, "ts");
            return (Criteria) this;
        }

        public Criteria andDrIsNull() {
            addCriterion("DR is null");
            return (Criteria) this;
        }

        public Criteria andDrIsNotNull() {
            addCriterion("DR is not null");
            return (Criteria) this;
        }

        public Criteria andDrEqualTo(String value) {
            addCriterion("DR =", value, "dr");
            return (Criteria) this;
        }

        public Criteria andDrNotEqualTo(String value) {
            addCriterion("DR <>", value, "dr");
            return (Criteria) this;
        }

        public Criteria andDrGreaterThan(String value) {
            addCriterion("DR >", value, "dr");
            return (Criteria) this;
        }

        public Criteria andDrGreaterThanOrEqualTo(String value) {
            addCriterion("DR >=", value, "dr");
            return (Criteria) this;
        }

        public Criteria andDrLessThan(String value) {
            addCriterion("DR <", value, "dr");
            return (Criteria) this;
        }

        public Criteria andDrLessThanOrEqualTo(String value) {
            addCriterion("DR <=", value, "dr");
            return (Criteria) this;
        }

        public Criteria andDrLike(String value) {
            addCriterion("DR like", value, "dr");
            return (Criteria) this;
        }

        public Criteria andDrNotLike(String value) {
            addCriterion("DR not like", value, "dr");
            return (Criteria) this;
        }

        public Criteria andDrIn(List<String> values) {
            addCriterion("DR in", values, "dr");
            return (Criteria) this;
        }

        public Criteria andDrNotIn(List<String> values) {
            addCriterion("DR not in", values, "dr");
            return (Criteria) this;
        }

        public Criteria andDrBetween(String value1, String value2) {
            addCriterion("DR between", value1, value2, "dr");
            return (Criteria) this;
        }

        public Criteria andDrNotBetween(String value1, String value2) {
            addCriterion("DR not between", value1, value2, "dr");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}