<?xml version="1.0" encoding="utf8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="https://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" encoding="utf8"/>
		<xsl:template match="C.account.xml">
			<xsl:apply-templates/>
				<Classes>
					<xsl:for-each select="class">
						<class>
							<账号>
								<xsl:value-of select="acc"/>
							</账号>
							<密码>
								<xsl:value-of select="passwd"/>
							</密码>
							<创建日期>
								<xsl:value-of select="CreateDate"/>
							</创建日期>
						</class>
					</xsl:for-each>	
				</Classes>
			</xsl:template>

</xsl:stylesheet>
